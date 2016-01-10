import javax.swing.*;

/**
 * Created by Tymek on 10.01.2016.
 */
public class BuildingInspector {
    private JLabel xLabel;
    private JLabel yLabel;
    private JPanel jpanel;
    private JLabel vehicleTypeLabel;
    private JList peopleList;
    private JList vehiclesList;
    private final JFrame frame;
    private Building selectedBuilding;

    public BuildingInspector() {
        this.frame = new JFrame("Vehicle Inspector");
        this.frame.setContentPane(this.jpanel);
        this.frame.setLocation(0, WorldController.getControlPanel().getFrame().getHeight());
        this.frame.pack();
        this.infiniteLoop();
    }

    private void infiniteLoop() {
        Runnable r = () -> {
            while (true) {
                if (this.selectedBuilding != null) {
                    this.xLabel.setText(String.valueOf(this.selectedBuilding.getPosition().getX()));
                    this.yLabel.setText(String.valueOf(this.selectedBuilding.getPosition().getY()));
                    this.vehiclesList.setListData(this.selectedBuilding.getVehicles().toArray());
                    this.peopleList.setListData(this.selectedBuilding.getPeople().toArray());

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

    public JLabel getyLabel() {
        return this.yLabel;
    }

    public JLabel getxLabel() {
        return this.xLabel;
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public Building getSelectedBuilding() {
        return this.selectedBuilding;
    }

    public void setSelectedBuilding(Building selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
    }

    public void setVehicleTypeLabel(String v) {
        this.vehicleTypeLabel.setText(String.valueOf(v));
    }
}
