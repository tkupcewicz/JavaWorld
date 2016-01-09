import javax.swing.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Tymek on 08.01.2016.
 */
public class VehicleInspector {
    private JButton emergencyLandingButton;
    private JPanel jpanel;
    private JLabel xLabel;
    private JLabel yLabel;
    private JList routeList;
    private JList passengerList;
    private JLabel nextDestinationLabel;
    private JButton deleteButton;
    private JButton button1;
    private JButton button2;
    private JButton spawnPlaneButton;
    private JScrollPane passengerPane;
    private JLabel passengersLabel;
    private JFrame frame;
    private Vehicle selectedVehicle;

    public Vehicle getSelectedVehicle() {
        return selectedVehicle;
    }

    public void setSelectedVehicle(Vehicle selectedVehicle) {
        this.selectedVehicle = selectedVehicle;
    }

    public VehicleInspector(){
        frame = new JFrame("Vehicle Inspector");
        frame.setContentPane(this.jpanel);
        frame.pack();
        infiniteLoop();

        emergencyLandingButton.addActionListener(e -> {
            selectedVehicle.flyToNearest();

        });
        spawnPlaneButton.addActionListener(e -> {
            this.spawnMilitaryPlane();
        });
    }

    public JFrame getFrame() {
        return frame;
    }

    public void infiniteLoop() {
        Runnable r = () -> {
            while (true) {
                if(selectedVehicle != null){
                    nextDestinationLabel.setText(String.valueOf(selectedVehicle.getNextDestination()));
                    xLabel.setText(String.valueOf(selectedVehicle.getPosition().getX()));
                    yLabel.setText(String.valueOf(selectedVehicle.getPosition().getY()));
                    routeList.setListData(selectedVehicle.getRoute().toArray());
                }

                jpanel.repaint();

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    public JButton getSpawnPlaneButton() {
        return spawnPlaneButton;
    }

    public void spawnMilitaryPlane(){
        Runnable r = new MilitaryAircraft(selectedVehicle.getPosition());
        Thread t = new Thread(r);
        t.start();

    }

    public JList getPassengerList() {
        return passengerList;
    }

    public JScrollPane getPassengerPane() {
        return passengerPane;
    }

    public JLabel getPassengersLabel() {
        return passengersLabel;
    }
}
