import javax.swing.*;

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
    private JButton addRouteButton;
    private JButton delRouteButton;
    private JButton spawnPlaneButton;
    private JScrollPane passengerPane;
    private JLabel passengersLabel;
    private JLabel idLabel;
    private JProgressBar fuelBar;
    private JLabel companyNameLabel;
    private JLabel companyValueLabel;
    private final JFrame frame;
    private Vehicle selectedVehicle;

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

    public Vehicle getSelectedVehicle() {
        return this.selectedVehicle;
    }

    public void setSelectedVehicle(Vehicle selectedVehicle) {
        this.selectedVehicle = selectedVehicle;
    }

    public JFrame getFrame() {
        return this.frame;
    }

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

    public JButton getSpawnPlaneButton() {
        return this.spawnPlaneButton;
    }

    private void spawnMilitaryPlane() {
        Runnable r = new MilitaryAircraft(this.selectedVehicle.getPosition());
        Thread t = new Thread(r);
        t.start();

    }

    public JList getPassengerList() {
        return this.passengerList;
    }

    public JScrollPane getPassengerPane() {
        return this.passengerPane;
    }

    public JLabel getPassengersLabel() {
        return this.passengersLabel;
    }

    public void delRoute(Building b) {
        int x = this.selectedVehicle.getRoute().indexOf(b);
        this.selectedVehicle.getRoute().subList(x, this.selectedVehicle.getRoute().size()).clear();
    }

    public JLabel getCompanyNameLabel() {
        return this.companyNameLabel;
    }

    public JLabel getCompanyValueLabel() {
        return this.companyValueLabel;
    }

    public JButton getEmergencyLandingButton() {
        return this.emergencyLandingButton;
    }
}
