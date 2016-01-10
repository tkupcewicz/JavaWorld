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

        mainFrame = new JFrame("JavaWorld");

        setPreferredSize(new Dimension(1050, 800));

        mainFrame.getContentPane().add(this);

        mainFrame.pack();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setLocation(controlPanel.getFrame().getWidth(), 0);

        mainFrame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelected(null);
                for (PhysicalObject object : mainMap.getObjectsToDraw()) {
                    Rectangle rect;
                    if (object.getImage() != null) {
                        rect = new Rectangle((int) object.getPosition().getX() - object.getImage().getWidth() / 2,
                                (int) object.getPosition().getY() - object.getImage().getHeight() / 2,
                                object.getImage().getWidth(),
                                object.getImage().getHeight());
                        if (rect.contains(e.getX(), e.getY() - mainFrame.getInsets().top)) {
                            setSelected(object);
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
        mainFrame.setVisible(true);
        infiniteLoop();

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
    }

    public static Map getMainMap() {
        return mainMap;
    }

    public static ControlPanel getControlPanel() {
        return controlPanel;
    }

    private PhysicalObject getSelected() {
        return selected;
    }

    private void setSelected(PhysicalObject selected) {
        this.selected = selected;
    }

    private void infiniteLoop() {
        Runnable r = () -> {
            while (true) {
                repaint();
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
        drawSelection(g);
    }

    private void drawSelection(Graphics g) {
        if (getSelected() != null) {
            g.drawRect((int) getSelected().getPosition().getX() - getSelected().getImage().getWidth() / 2,
                    (int) getSelected().getPosition().getY() - getSelected().getImage().getWidth() / 2,
                    getSelected().getImage().getHeight(),
                    getSelected().getImage().getWidth());
        }
    }
}