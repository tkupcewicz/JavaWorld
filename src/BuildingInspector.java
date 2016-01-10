import javax.swing.*;
import java.io.Serializable;

/**
 * Created by Tymek on 10.01.2016.
 */

/**
 * Building Inspector class
 */
public class BuildingInspector implements Serializable{
    private JLabel xLabel;
    private JLabel yLabel;
    private JPanel jpanel;
    private JLabel vehicleTypeLabel;
    private JList peopleList;
    private JList vehiclesList;
    private final JFrame frame;
    private Building selectedBuilding;

    /**
     * Constructor which creates frame and starts rendering loop
     */
    public BuildingInspector() {
        this.frame = new JFrame("Vehicle Inspector");
        this.frame.setContentPane(this.jpanel);
        this.frame.setLocation(0, WorldController.getControlPanel().getFrame().getHeight());
        this.frame.pack();
        this.infiniteLoop();
    }

    /**
     * Rendering loop which refreshes at rate of 5 frames per second
     */
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

    /**
     *
     * @return returns frame of Building Inspector
     */
    public JFrame getFrame() {
        return this.frame;
    }

    /**
     * Sets selected building at given Building
     * @param selectedBuilding building
     */
    public void setSelectedBuilding(Building selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
    }

    /**
     * Sets label of type of vehicles inside Building.
     * @param v string
     */
    public void setVehicleTypeLabel(String v) {
        this.vehicleTypeLabel.setText(String.valueOf(v));
    }
}
