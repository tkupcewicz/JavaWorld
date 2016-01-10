import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Tymek on 30.12.2015.
 */

/**
 * Building class
 */
public abstract class Building extends PhysicalObject {
    private LinkedList<Building> connections;
    private ConcurrentLinkedQueue<Vehicle> vehicles;
    private ConcurrentLinkedQueue<Passenger> people;
    private int buildingCapacity;

    /**
     *
     * @return returns connections of Building
     */
    public LinkedList<Building> getConnections() {
        return this.connections;
    }

    /**
     * Default constructor which creates lists
     */
    public Building() {
        this.people = new ConcurrentLinkedQueue<>();
        this.buildingCapacity = MapConfig.getMaxAirportCapacity();
        this.vehicles = new ConcurrentLinkedQueue<>();
    }

    /**
     * Sets connections of Building
     * @param connections LinkedList of connections
     */
    void setConnections(LinkedList connections) {
        this.connections = connections;
    }

    /**
     *
     * @param i position of connection in list
     * @return returns connection of given number
     */
    public Building getConnections(int i) {
        return this.connections.get(i);
    }

    /**
     * Adds Building to list of connections
     * @param b building
     */
    public void addConnection(Building b) {
        this.connections.add(b);
    }

    /**
     * Shows right buttons and labels in Building Inspector for Building
     */
    @Override
    void inspect() {
        WorldController.getBuildingInspector().getFrame().setVisible(true);
        WorldController.getVehicleInspector().getFrame().setVisible(false);
        WorldController.getBuildingInspector().setSelectedBuilding(this);
    }

    /**
     * Adds Vehicle to Building's vehicles
     * @param v vehicle
     */
    void addToVehicles(Vehicle v){
        this.vehicles.add(v);
    }

    /**
     * Deletes Vehicle from Buildings' vehicles list
     * @param v vehicle
     */
    void deleteFromVehicles(Vehicle v){
        this.vehicles.remove(v);
    }

    /**
     *
     * @return returns list of Building's vehicles list
     */
    public ConcurrentLinkedQueue<Vehicle> getVehicles() {
        return this.vehicles;
    }

    /**
     * Adds Passenger to Building's people list
     * @param p passenger
     */
    void addToPeople(Passenger p){
        this.people.add(p);
    }

    /**
     * Deletes Passenger from Building's people list
     * @param p passenger
     */
    void deleteFromPeople(Passenger p){
        this.people.remove(p);
    }

    /**
     *
     * @return returns list of Building's people
     */
    public ConcurrentLinkedQueue<Passenger> getPeople() {
        return this.people;
    }

    public abstract Building getRandomConnected();

    /**
     *
     * @return building capacity of vehicles
     */
    public int getBuildingCapacity() {
        return this.buildingCapacity;
    }
}
