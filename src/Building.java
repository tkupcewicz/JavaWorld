import java.util.LinkedList;

/**
 * Created by Tymek on 30.12.2015.
 */
public abstract class Building extends PhysicalObject {
    private LinkedList<Building> connections;

    public LinkedList<Building> getConnections() {
        return connections;
    }

    void setConnections(LinkedList connections) {
        this.connections = connections;
    }

    public Building getConnections(int i) {
        return connections.get(i);
    }

    public void addConnection(Building b) {
        connections.add(b);
    }


    @Override
    void inspect() {
        WorldController.getBuildingInspector().getFrame().setVisible(true);
        WorldController.getVehicleInspector().getFrame().setVisible(false);
        WorldController.getBuildingInspector().setSelectedBuilding(this);
    }
}
