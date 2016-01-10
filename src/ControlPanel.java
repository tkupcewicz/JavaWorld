import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

/**
 * Created by Tymek on 31.12.2015.
 */
public class ControlPanel {
    private JPanel panel1;
    private JCheckBox crossroadBox;
    private JCheckBox pathBox;
    private JButton button1;
    private JSlider vehiclesSpeedSlider;
    private JButton spawnPassengerShipButton;
    private JButton spawnAircraftCarrierButton;
    private final JFrame frame;

    public ControlPanel() {

        this.frame = new JFrame("Control Panel");

        this.crossroadBox.setSelected(MapConfig.isCrossroadVisible());
        this.pathBox.setSelected(MapConfig.isPathVisible());

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
        this.button1.addActionListener(e -> this.spawnPlane());
        this.spawnPassengerShipButton.addActionListener(e -> this.spawnPassShip());
        this.spawnAircraftCarrierButton.addActionListener(e -> this.spawnCarrier());
        Hashtable labelTable = new Hashtable();
        labelTable.put(10, new JLabel("10%"));
        labelTable.put(100, new JLabel("100%"));
        labelTable.put(300, new JLabel("300%"));
        this.vehiclesSpeedSlider.setLabelTable(labelTable);


    }

    private void spawnPlane() {
        Runnable r = new PassengerAircraft();
        Thread t = new Thread(r);
        t.start();
    }

    private void spawnPassShip() {
        Runnable r = new PassengerShip();
        Thread t = new Thread(r);
        t.start();
    }

    private void spawnCarrier() {
        Runnable r = new AircraftCarrier();
        Thread t = new Thread(r);
        t.start();
    }

    public JSlider getVehiclesSpeedSlider() {
        return this.vehiclesSpeedSlider;
    }

    public JPanel getPanel1() {
        return this.panel1;
    }

    public JFrame getFrame() {
        return this.frame;
    }
}

