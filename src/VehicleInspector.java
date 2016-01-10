import javax.swing.*;
import java.io.Serializable;

/**
 * Created by Tymek on 08.01.2016.
 */

/**
 * Vehicle Inspector class
 */
public class VehicleInspector implements Serializable {
    private JButton emergencyLandingButton;
    private JPanel jpanel;
    private JLabel xLabel;
    private JLabel yLabel;
    private JList routeList;
    private JList passengerList;
    private JLabel nextDestinationLabel;
    private JButton deleteButton;
    private JButton addRouteButton;
    private JButton delRouteButton;
    private JButton spawnPlaneButton;
    private JScrollPane passengerPane;
    private JLabel passengersLabel;
    private JLabel idLabel;
    private JProgressBar fuelBar;
    private JLabel companyNameLabel;
    private JLabel companyValueLabel;
    private JLabel maxPassLabel;
    private JLabel actPassLabel;
    private JLabel actPassValueLabel;
    private JLabel maxPassValue;
    private final JFrame frame;
    private Vehicle selectedVehicle;

    /**
     * Constructor which adds listener and disables buttons which will be implemented later
     */
    public VehicleInspector() {
        this.frame = new JFrame("Vehicle Inspector");
        this.frame.setContentPane(this.jpanel);
        this.frame.setLocation(0, WorldController.getControlPanel().getFrame().getHeight());
        this.frame.pack();
        this.infiniteLoop();

        this.emergencyLandingButton.addActionListener(e -> this.selectedVehicle.flyToNearest());
        this.spawnPlaneButton.addActionListener(e -> this.spawnMilitaryPlane());
        this.delRouteButton.setEnabled(false);
        this.addRouteButton.setEnabled(false);

    }

    /**
     * Sets selected Vehicle of Inspector
     * @param selectedVehicle vehicle
     */
    public void setSelectedVehicle(Vehicle selectedVehicle) {
        this.selectedVehicle = selectedVehicle;
    }

    /**
     *
     * @return returns frame of Vehicle Inspector
     */
    public JFrame getFrame() {
        return this.frame;
    }

    /**
     * Rendering loop which renders all elements at given frame rate of 5 frames per second
     */
    private void infiniteLoop() {
        Runnable r = () -> {
            while (true) {
                if (this.selectedVehicle != null) {
                    this.fuelBar.setMaximum((int) this.selectedVehicle.getMaxFuel());
                    this.nextDestinationLabel.setText(String.valueOf(this.selectedVehicle.getNextDestination()));
                    this.xLabel.setText(String.valueOf(this.selectedVehicle.getPosition().getX()));
                    this.yLabel.setText(String.valueOf(this.selectedVehicle.getPosition().getY()));
                    this.idLabel.setText(String.valueOf(this.selectedVehicle.getUniqueId()));
                    this.routeList.setListData(this.selectedVehicle.getRoute().toArray());
                    this.actPassValueLabel.setText(String.valueOf(this.selectedVehicle.getPassengerLinkedList().size()));
                    this.maxPassValue.setText(String.valueOf(this.selectedVehicle.getMaxPassengerCount()));
                    this.fuelBar.setValue((int) this.selectedVehicle.getActualFuel());
                    this.routeList.setEnabled(false);
                    this.passengerList.setListData(this.selectedVehicle.getPassengerLinkedList().toArray());
                    this.deleteButton.setEnabled(false);
                }

                this.jpanel.repaint();

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

    /**
     *
     * @return returns button which spawns Passenger Planes
     */
    public JButton getSpawnPlaneButton() {
        return this.spawnPlaneButton;
    }

    /**
     * Creates new Military Plane with it's own thread
     */
    private void spawnMilitaryPlane() {
        Runnable r = new MilitaryAircraft(this.selectedVehicle.getPosition());
        Thread t = new Thread(r);
        t.start();

    }

    /**
     *
     * @return returns pane of passengers
     */
    public JScrollPane getPassengerPane() {
        return this.passengerPane;
    }

    /**
     *
     * @return returns passengers label
     */
    public JLabel getPassengersLabel() {
        return this.passengersLabel;
    }

    /**
     * Method which should delete fragment of route from given position till the end
     * @param b position at list
     */
    public void delRoute(Building b) {
        int x = this.selectedVehicle.getRoute().indexOf(b);
        this.selectedVehicle.getRoute().subList(x, this.selectedVehicle.getRoute().size()).clear();
    }

    /**
     *
     * @return returns company name label
     */
    public JLabel getCompanyNameLabel() {
        return this.companyNameLabel;
    }

    /**
     *
     * @return returns company value label
     */
    public JLabel getCompanyValueLabel() {
        return this.companyValueLabel;
    }

    /**
     *
     * @return returns emergancy landing button
     */
    public JButton getEmergencyLandingButton() {
        return this.emergencyLandingButton;
    }

    /**
     *
     * @return max passenger label
     */
    public JLabel getMaxPassLabel() {
        return this.maxPassLabel;
    }

    /**
     *
     * @return returns actual passenger label
     */
    public JLabel getActPassLabel() {
        return this.actPassLabel;
    }

    /**
     *
     * @return returns label of actual passenger value
     */
    public JLabel getActPassValueLabel() {
        return this.actPassValueLabel;
    }

    /**
     *
     * @return returns label of max passenger value
     */
    public JLabel getMaxPassValue() {
        return this.maxPassValue;
    }
}
