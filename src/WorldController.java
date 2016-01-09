import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class WorldController extends JPanel {

    private static WorldController worldController;
    private static ControlPanel controlPanel;
    private static VehicleInspector vehicleInspector;
    private static Map mainMap;
    private JFrame mainFrame;


    private PhysicalObject selected;

    public PhysicalObject getSelected() {
        return selected;
    }

    public void setSelected(PhysicalObject selected) {
        this.selected = selected;
    }

    public static WorldController getWorldController() {
        return worldController;
    }

    public static VehicleInspector getVehicleInspector() {
        return vehicleInspector;
    }

    public WorldController() {

        mainFrame = new JFrame("JavaWorld");

        this.setPreferredSize(new Dimension(1200,800));

        mainFrame.getContentPane().add(this);

        mainFrame.pack();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelected(null);
                for (PhysicalObject object:mainMap.getObjectsToDraw()) {
                    Rectangle rect = null;
                    if(object.getImage() != null) {
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

    public void infiniteLoop() {
        Runnable r = () -> {
            while (true) {
                repaint();
                if(controlPanel != null){
                    MapConfig.setVehiclesSpeed(controlPanel.getVehiclesSpeedSlider().getValue()/100.0f);
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

    public static void main(String args[]) {
        mainMap = new Map();
        worldController = new WorldController();
        controlPanel = new ControlPanel();
        vehicleInspector = new VehicleInspector();
    }

    public static Map getMainMap() {
        return mainMap;
    }

    public void drawSelection(Graphics g){
        if(getSelected() != null){
            g.drawRect((int) getSelected().getPosition().getX() - (getSelected().getImage().getWidth() / 2),
                    (int) getSelected().getPosition().getY() - (getSelected().getImage().getWidth() / 2),
                    getSelected().getImage().getHeight(),
                    getSelected().getImage().getWidth());
        }
    }
}