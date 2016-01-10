import javax.swing.*;

/**
 * Created by Tymek on 10.01.2016.
 */
public class BuildingInspector {
    private JList list1;
    private JList list2;
    private JLabel xLabel;
    private JLabel yLabel;
    private JPanel jpanel;
    private JLabel vehicleTypeLabel;
    private final JFrame frame;
    private Building selectedBuilding;

    public BuildingInspector() {
        frame = new JFrame("Vehicle Inspector");
        frame.setContentPane(jpanel);
        frame.setLocation(0, WorldController.getControlPanel().getFrame().getHeight());
        frame.pack();
        infiniteLoop();
    }

    private void infiniteLoop() {
        Runnable r = () -> {
            while (true) {
                if (selectedBuilding != null) {
                    xLabel.setText(String.valueOf(selectedBuilding.getPosition().getX()));
                    yLabel.setText(String.valueOf(selectedBuilding.getPosition().getY()));

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

    public JLabel getyLabel() {
        return yLabel;
    }

    public JLabel getxLabel() {
        return xLabel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public void setSelectedBuilding(Building selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
    }

    public void setVehicleTypeLabel(String v) {
        vehicleTypeLabel.setText(String.valueOf(v));
    }
}
