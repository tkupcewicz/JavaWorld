import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Hashtable;

/**
 * Created by Tymek on 31.12.2015.
 */

/**
 * Class of Control Panel necessary for spawning Vehicles
 */
public class ControlPanel implements Serializable{
    private JPanel panel1;
    private JCheckBox crossroadBox;
    private JCheckBox pathBox;
    private JButton button1;
    private JSlider vehiclesSpeedSlider;
    private JButton spawnPassengerShipButton;
    private JButton spawnAircraftCarrierButton;
    private JButton saveButton;
    private JButton loadButton;
    private JCheckBox showBackgroundCheckBox;
    private final JFrame frame;

    /**
     * Constructor setting up button listeners and disabling buttons which may implemented in future
     */
    public ControlPanel() {

        this.frame = new JFrame("Control Panel");
        this.crossroadBox.setSelected(MapConfig.isCrossroadVisible());
        this.pathBox.setSelected(MapConfig.isPathVisible());
        this.showBackgroundCheckBox.setSelected(MapConfig.isBackgroundVisible());
        this.frame.setContentPane(this.panel1);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setMinimumSize(new Dimension(100, 100));
        this.frame.setVisible(true);

        this.crossroadBox.addItemListener(e -> {

            if (e.getStateChange() == 1) {
                MapConfig.setCrossroadVisible(true);
            } else {
                MapConfig.setCrossroadVisible(false);
            }
        });
        this.pathBox.addItemListener(e -> {

            if (e.getStateChange() == 1) {
                MapConfig.setPathVisible(true);
            } else {
                MapConfig.setPathVisible(false);
            }
        });
        this.showBackgroundCheckBox.addItemListener(e -> {

            if (e.getStateChange() == 1) {
                MapConfig.setBackgroundVisible(true);
            } else {
                MapConfig.setBackgroundVisible(false);
            }
        });
        this.button1.addActionListener(e -> this.spawnPlane());
        this.spawnPassengerShipButton.addActionListener(e -> this.spawnPassShip());
        this.spawnAircraftCarrierButton.addActionListener(e -> this.spawnCarrier());
        Hashtable labelTable = new Hashtable();
        labelTable.put(10, new JLabel("10%"));
        labelTable.put(100, new JLabel("100%"));
        labelTable.put(300, new JLabel("300%"));
        this.vehiclesSpeedSlider.setLabelTable(labelTable);

        this.saveButton.addActionListener(e -> this.saveWorld());
        this.loadButton.addActionListener(e -> this.loadWorld());
        this.loadButton.setEnabled(false);
        //  this.saveButton.setEnabled(false);


    }

    /**
     * Creates new Passenger Aircraft with it's own thread
     */
    private void spawnPlane() {
        Runnable r = new PassengerAircraft();
        Thread t = new Thread(r);
        t.start();
    }
    /**
     * Creates new Passenger Ship with it's own thread
     */
    private void spawnPassShip() {
        Runnable r = new PassengerShip();
        Thread t = new Thread(r);
        t.start();
    }
    /**
     * Creates new Aircraft Carrier with it's own thread
     */
    private void spawnCarrier() {
        Runnable r = new AircraftCarrier();
        Thread t = new Thread(r);
        t.start();
    }

    /**
     *
     * @return returns vehicles speed slider
     */
    public JSlider getVehiclesSpeedSlider() {
        return this.vehiclesSpeedSlider;
    }

    /**
     *
     * @return returns Control Panel frame
     */
    public JFrame getFrame() {
        return this.frame;
    }

    /**
     * Saves the World to binary file of given name in MapConfig.
     */
    public void saveWorld(){
        String filename = MapConfig.getSerFileName();
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(filename)));
            out.writeObject(WorldController.getWorldController());
            out.close();
            System.out.println("Saved");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Should read binary file of given name in MapConfig and set World Controller in it to actual World Controller.
     * Doesn't work.
     */
    public void loadWorld(){
        String filename = MapConfig.getSerFileName();

        try {
            ObjectInputStream in = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(filename)));
            WorldController.setMainMap(null);
            WorldController.setWorldController(null);
            Thread.sleep(100);
            WorldController.setWorldController((WorldController) in.readObject());
            System.out.println("Loaded");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

