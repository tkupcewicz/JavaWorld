import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Tymek on 30.12.2015.
 */
public abstract class Building extends PhysicalObject {
    private LinkedList<Building> connections;
    private ConcurrentLinkedQueue<Vehicle> vehicles;
    private ConcurrentLinkedQueue<Passenger> people;
    private int buildingCapacity;

    public LinkedList<Building> getConnections() {
        return this.connections;
    }

    public Building() {
        this.people = new ConcurrentLinkedQueue<>();
        this.buildingCapacity = MapConfig.getMaxAirportCapacity();
        this.vehicles = new ConcurrentLinkedQueue<>();
    }

    void setConnections(LinkedList connections) {
        this.connections = connections;
    }

    public Building getConnections(int i) {
        return this.connections.get(i);
    }

    public void addConnection(Building b) {
        this.connections.add(b);
    }


    @Override
    void inspect() {
        WorldController.getBuildingInspector().getFrame().setVisible(true);
        WorldController.getVehicleInspector().getFrame().setVisible(false);
        WorldController.getBuildingInspector().setSelectedBuilding(this);
    }

    void addToVehicles(Vehicle v){
        this.vehicles.add(v);
    }
    void deleteFromVehicles(Vehicle v){
        this.vehicles.remove(v);
    }

    public ConcurrentLinkedQueue<Vehicle> getVehicles() {
        return this.vehicles;
    }
    void addToPeople(Passenger p){
        this.people.add(p);
    }
    void deleteFromPeople(Passenger p){
        this.people.remove(p);
    }

    public ConcurrentLinkedQueue<Passenger> getPeople() {
        return this.people;
    }

    public abstract Building getRandomConnected();

}
