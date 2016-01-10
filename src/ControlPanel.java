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

        frame = new JFrame("Control Panel");

        crossroadBox.setSelected(MapConfig.isCrossroadVisible());
        pathBox.setSelected(MapConfig.isPathVisible());

        frame.setContentPane(panel1);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(100, 100));
        frame.setVisible(true);

        crossroadBox.addItemListener(e -> {

            if (e.getStateChange() == 1) {
                MapConfig.setCrossroadVisible(true);
            } else {
                MapConfig.setCrossroadVisible(false);
            }
        });
        pathBox.addItemListener(e -> {

            if (e.getStateChange() == 1) {
                MapConfig.setPathVisible(true);
            } else {
                MapConfig.setPathVisible(false);
            }
        });
        button1.addActionListener(e -> spawnPlane());
        spawnPassengerShipButton.addActionListener(e -> spawnPassShip());
        spawnAircraftCarrierButton.addActionListener(e -> spawnCarrier());
        Hashtable labelTable = new Hashtable();
        labelTable.put(10, new JLabel("10%"));
        labelTable.put(100, new JLabel("100%"));
        labelTable.put(300, new JLabel("300%"));
        vehiclesSpeedSlider.setLabelTable(labelTable);


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
        return vehiclesSpeedSlider;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JFrame getFrame() {
        return frame;
    }
}

