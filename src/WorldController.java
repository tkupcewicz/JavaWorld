import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WorldController extends JPanel {

    private static WorldController worldController;
    private static ControlPanel controlPanel;
    private static VehicleInspector vehicleInspector;
    private static BuildingInspector buildingInspector;
    private static Map mainMap;
    private final JFrame mainFrame;


    private PhysicalObject selected;

    private WorldController() {

        this.mainFrame = new JFrame("JavaWorld");

        this.setPreferredSize(new Dimension(1050, 800));

        this.mainFrame.getContentPane().add(this);

        this.mainFrame.pack();

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainFrame.setLocation(controlPanel.getFrame().getWidth(), 0);

        this.mainFrame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                WorldController.this.setSelected(null);
                for (PhysicalObject object : mainMap.getObjectsToDraw()) {
                    Rectangle rect;
                    if (object.getImage() != null) {
                        rect = new Rectangle((int) object.getPosition().getX() - object.getImage().getWidth() / 2,
                                (int) object.getPosition().getY() - object.getImage().getHeight() / 2,
                                object.getImage().getWidth(),
                                object.getImage().getHeight());
                        if (rect.contains(e.getX(), e.getY() - WorldController.this.mainFrame.getInsets().top)) {
                            WorldController.this.setSelected(object);
                            object.inspect();
                            break;
                        }
                    }
                    vehicleInspector.getFrame().setVisible(false);
                    buildingInspector.getFrame().setVisible(false);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        this.mainFrame.setVisible(true);
        this.infiniteLoop();

    }

    public static WorldController getWorldController() {
        return worldController;
    }

    public static VehicleInspector getVehicleInspector() {
        return vehicleInspector;
    }

    public static BuildingInspector getBuildingInspector() {
        return buildingInspector;
    }

    public static void main(String args[]) {
        mainMap = new Map();
        controlPanel = new ControlPanel();
        vehicleInspector = new VehicleInspector();
        buildingInspector = new BuildingInspector();
        worldController = new WorldController();

        for (int i = 0; i < 500; i++) {
            mainMap.spawnPassenger();
        }
    }

    public static Map getMainMap() {
        return mainMap;
    }

    public static ControlPanel getControlPanel() {
        return controlPanel;
    }

    private PhysicalObject getSelected() {
        return this.selected;
    }

    private void setSelected(PhysicalObject selected) {
        this.selected = selected;
    }

    private void infiniteLoop() {
        Runnable r = () -> {
            while (true) {
                this.repaint();
                if (controlPanel != null) {
                    MapConfig.setVehiclesSpeed(controlPanel.getVehiclesSpeedSlider().getValue() / 100.0f);
                }
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < mainMap.getObjectsToDraw().size(); i++) {
            mainMap.getObjectsToDraw().get(i).drawImage(g);
        }
        this.drawSelection(g);
    }

    private void drawSelection(Graphics g) {
        if (this.getSelected() != null) {
            g.drawRect((int) this.getSelected().getPosition().getX() - this.getSelected().getImage().getWidth() / 2,
                    (int) this.getSelected().getPosition().getY() - this.getSelected().getImage().getWidth() / 2,
                    this.getSelected().getImage().getHeight(),
                    this.getSelected().getImage().getWidth());
        }
    }
}