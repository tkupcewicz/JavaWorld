import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

/**
 * Main class which contains Main method and renders World
 */
public class WorldController extends JPanel implements Serializable {

    private static WorldController worldController;
    private static ControlPanel controlPanel;
    private static VehicleInspector vehicleInspector;
    private static BuildingInspector buildingInspector;
    private static Map mainMap;
    private final JFrame mainFrame;
    private PhysicalObject selected;

    /**
     * Constructor sets new JFrame at given size. Adds Mouse Listeners which is used to control Inspectors.
     * Begins infinite loop.
     */
    private WorldController() {

        this.mainFrame = new JFrame("JavaWorld");

        this.setPreferredSize(new Dimension(1050, 800));

        this.mainFrame.getContentPane().add(this);

        this.mainFrame.pack();

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainFrame.setLocation(controlPanel.getFrame().getWidth(), 0);

        this.mainFrame.addMouseListener(new MouseListener() {
            /**
             * Checks on left mouse click if there's something under mouse coordinates.
             * If yes it selects it and shows proper Inspector.
             * Else hides Inspectors.
             * @param e event
             */
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

    /**
     *
     * @return returns worldController
     */
    public static WorldController getWorldController() {
        return worldController;
    }

    /**
     *
     * @return returns vehicleInspector
     */
    public static VehicleInspector getVehicleInspector() {
        return vehicleInspector;
    }

    /**
     *
     * @return returns buildingInspector
     */
    public static BuildingInspector getBuildingInspector() {
        return buildingInspector;
    }

    /**
     * Main method, creates Map, Control Panel and both Inspectors.
     * @param args unused arguments
     */
    public static void main(String args[]) {
        mainMap = new Map();
        controlPanel = new ControlPanel();
        vehicleInspector = new VehicleInspector();
        buildingInspector = new BuildingInspector();
        worldController = new WorldController();
    }

    /**
     *
     * @return returns mainMap
     */
    public static Map getMainMap() {
        return mainMap;
    }

    /**
     *
     * @return controlPanel
     */
    public static ControlPanel getControlPanel() {
        return controlPanel;
    }

    /**
     *
     * @return returns selected Physical Object
     */
    private PhysicalObject getSelected() {
        return this.selected;
    }

    /**
     * Sets object as selected
     * @param selected physical object
     */
    private void setSelected(PhysicalObject selected) {
        this.selected = selected;
    }

    /**
     * Loop of function which is infinite and calls redraw every 16 ms to get 60 frames per second experience
     */
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
        if(MapConfig.isBackgroundVisible()){
            g.drawImage(MapConfig.getBackgroundImg(), 0, 0, this);
        }
        for (int i = 0; i < mainMap.getObjectsToDraw().size(); i++) {
            mainMap.getObjectsToDraw().get(i).drawImage(g);
        }
        this.drawSelection(g);
    }

    /**
     * Draws rectangle around selected object
     * @param g graphic
     */
    private void drawSelection(Graphics g) {
        if (this.getSelected() != null) {
            g.drawRect((int) this.getSelected().getPosition().getX() - this.getSelected().getImage().getWidth() / 2,
                    (int) this.getSelected().getPosition().getY() - this.getSelected().getImage().getWidth() / 2,
                    this.getSelected().getImage().getHeight(),
                    this.getSelected().getImage().getWidth());
        }
    }

    /**
     * Sets world controller.
     * Used for unsuccessful attempt of deserialization
     * @param worldController world controller
     */
    public static void setWorldController(WorldController worldController) {
        WorldController.worldController = worldController;
    }
    /**
     * Sets main map
     * Used for unsuccessful attempt of deserialization
     * @param mainMap map
     */
    public static void setMainMap(Map mainMap) {
        WorldController.mainMap = mainMap;
    }
}