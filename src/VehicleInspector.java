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
        frame = new JFrame("Vehicle Inspector");
        frame.setContentPane(jpanel);
        frame.setLocation(0, WorldController.getControlPanel().getFrame().getHeight());
        frame.pack();
        infiniteLoop();

        emergencyLandingButton.addActionListener(e -> selectedVehicle.flyToNearest());
        spawnPlaneButton.addActionListener(e -> spawnMilitaryPlane());
        delRouteButton.setEnabled(false);
        addRouteButton.setEnabled(false);

    }

    public Vehicle getSelectedVehicle() {
        return selectedVehicle;
    }

    public void setSelectedVehicle(Vehicle selectedVehicle) {
        this.selectedVehicle = selectedVehicle;
    }

    public JFrame getFrame() {
        return frame;
    }

    private void infiniteLoop() {
        Runnable r = () -> {
            while (true) {
                if (selectedVehicle != null) {
                    fuelBar.setMaximum((int) selectedVehicle.getMaxFuel());
                    nextDestinationLabel.setText(String.valueOf(selectedVehicle.getNextDestination()));
                    xLabel.setText(String.valueOf(selectedVehicle.getPosition().getX()));
                    yLabel.setText(String.valueOf(selectedVehicle.getPosition().getY()));
                    idLabel.setText(String.valueOf(selectedVehicle.getUniqueId()));
                    routeList.setListData(selectedVehicle.getRoute().toArray());
                    fuelBar.setValue((int) selectedVehicle.getActualFuel());
                    routeList.setEnabled(false);
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

    private void spawnMilitaryPlane() {
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

    public void delRoute(Building b) {
        int x = selectedVehicle.getRoute().indexOf(b);
        selectedVehicle.getRoute().subList(x, selectedVehicle.getRoute().size()).clear();
    }

    public JLabel getCompanyNameLabel() {
        return companyNameLabel;
    }

    public JLabel getCompanyValueLabel() {
        return companyValueLabel;
    }

    public JButton getEmergencyLandingButton() {
        return emergencyLandingButton;
    }
}
