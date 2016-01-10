import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tymek on 30.12.2015.
 */
public abstract class Building extends PhysicalObject {
    private LinkedList<Building> connections;
    public LinkedList<Building> getConnections() {
        return connections;
    }
    public Building getConnections(int i){
        return (Building) connections.get(i);
    }

    public void setConnections(LinkedList connections) {
        this.connections = connections;
    }
    public void addConnection(Building b) {this.connections.add(b);}


    public void inspect(){
        WorldController.getBuildingInspector().getFrame().setVisible(true);
        WorldController.getVehicleInspector().getFrame().setVisible(false);
        WorldController.getBuildingInspector().setSelectedBuilding(this);
    }
}
