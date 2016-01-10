import com.sun.javafx.geom.Point2D;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import static java.lang.Math.atan2;

/**
 * Created by Tymek on 13.10.15.
 */

/**
 * Vehicle class
 */
public abstract class Vehicle extends PhysicalObject implements Runnable {
    private String uniqueId;
    private float maxFuel;
    private float actualFuel;
    private int speed;
    private Building nextDestination;
    private Building currentBuilding;
    private LinkedList<Building> route;
    private ConcurrentLinkedQueue<Passenger> passengerLinkedList;
    private float distanceTravelled;
    private boolean moving;
    private boolean landed;
    private int maxPassengerCount;

    /**
     * Constructor which creates vehicle's list of connections, random UUID and fuel levels.
     */
    Vehicle() {
        this.passengerLinkedList = new ConcurrentLinkedQueue<>();
        this.setUniqueId(UUID.randomUUID().toString());
        this.setMaxFuel();
        this.setActualFuel(this.getMaxFuel());
        WorldController.getMainMap().getVehiclesList().add(this);
    }

    /**
     * Calculates distance which vehicle travels in X axis in one pass of loop
     * @param posX coordinate X of origin
     * @param posY coordinate Y of origin
     * @param tarX coordinate X of destination
     * @param tarY coordinate Y of destination
     * @return returns distance per frame
     */
    private float calculateXVel(float posX, float posY, float tarX, float tarY) {
        float angle = (float) atan2(tarY - posY, tarX - posX);
        return (float) (this.speed * Math.cos(angle));
    }
    /**
     * Calculates distance which vehicle travels in Y axis in one pass of loop
     * @param posX coordinate X of origin
     * @param posY coordinate Y of origin
     * @param tarX coordinate X of destination
     * @param tarY coordinate Y of destination
     * @return returns distance per frame
     */
    private float calculateYVel(float posX, float posY, float tarX, float tarY) {
        float angle = (float) atan2(tarY - posY, tarX - posX);
        return (float) (this.speed * Math.sin(angle));
    }

    /**
     * Loops through buildings adding them to route.
     * @param b current building
     */
    private void randomizeRoute(Building b) {
        Building tmp1 = b;
        for (int i = 0; i < MapConfig.randInt(MapConfig.getMinRouteLength(), MapConfig.getMaxRouteLength()); i++) {
            Building tmp2 = tmp1.getConnections().get(MapConfig.randInt(0, tmp1.getConnections().size() - 1));
            this.addToRoute(tmp2);
            tmp1 = tmp2;
        }
    }

    /**
     * Inifnite loop which calculates velocities, check state of vehicle and others
     */
    @Override
    public void run() {
        if (this.currentBuilding != null) {
            this.route = new LinkedList<>();
            this.randomizeRoute(this.currentBuilding);
            this.setNextDestination(this.route.get(0));
            this.route.remove(0);
        }
        float xVel = 0;
        float yVel = 0;
        float routeDistance = 0;
        this.distanceTravelled = 0;
        while (true) {
            if (!this.isMoving()) {
                Path temp = Path.calculatePath(this.getPosition(),
                        this.getNextDestination().getPosition(), -7);
                routeDistance = Point2D.distance(temp.getOrigin().getX(), temp.getOrigin().getY(),
                        temp.getDestination().getX(), temp.getDestination().getY());
                this.setPosition(temp.getDestination().getX(), temp.getDestination().getY());

                xVel = this.calculateXVel(temp.getDestination().getX(), temp.getDestination().getY(), temp.getOrigin().getX(), temp.getOrigin().getY());
                yVel = this.calculateYVel(temp.getDestination().getX(), temp.getDestination().getY(), temp.getOrigin().getX(), temp.getOrigin().getY());

                this.setMoving(true);
            } else {
                float tmpx = this.getPosition().getX();
                float tmpy = this.getPosition().getY();
                tmpx = tmpx + xVel * MapConfig.getVehiclesSpeed();
                tmpy = tmpy + yVel * MapConfig.getVehiclesSpeed();
                this.setActualFuel(this.getActualFuel() - 0.15f * MapConfig.getVehiclesSpeed());

                this.setPosition(tmpx, tmpy);
                this.distanceTravelled = (float) (this.distanceTravelled + Math.sqrt(Math.pow(xVel * MapConfig.getVehiclesSpeed(), 2) + Math.pow(yVel * MapConfig.getVehiclesSpeed(), 2)));

                if (this.distanceTravelled >= routeDistance) {
                    this.distanceTravelled = 0;
                    this.setMoving(false);
                    this.setPosition(this.nextDestination.getPosition());
                    if(nextDestination.getVehicles().size() < nextDestination.getBuildingCapacity()){
                        this.landed = true;
                        this.nextDestination.addToVehicles(this);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.nextDestination.deleteFromVehicles(this);
                        this.landed = false;
                    }
                    this.setCurrentBuilding(this.getNextDestination());
                    this.actualFuel = this.maxFuel;
                    if (this.route.size() == 1) {
                        this.setNextDestination(this.route.get(0));
                        this.route.remove(0);
                        this.randomizeRoute(this.nextDestination);
                    } else {
                        this.setNextDestination(this.route.get(0));
                        this.route.remove(0);
                    }
                }
            }

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @return returns next destination of vehicle
     */
    public Building getNextDestination() {
        return this.nextDestination;
    }

    /**
     * Sets next destination of vehicle
     * @param nextDestination Building
     */
    void setNextDestination(Building nextDestination) {
        this.nextDestination = nextDestination;
    }

    /**
     * Sets speed to given parameter
     * @param speed integer
     */
    void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     *
     * @return returns current building of vehicle
     */
    Building getCurrentBuilding() {
        return this.currentBuilding;
    }

    /**
     * Sets current building of vehicle
     * @param currentBuilding Building
     */
    void setCurrentBuilding(Building currentBuilding) {
        this.currentBuilding = currentBuilding;
    }

    /**
     *
     * @return returns boolean of vehicle moving
     */
    private boolean isMoving() {
        return this.moving;
    }

    /**
     * Sets vehicle mooving to given boolean
     * @param moving boolean
     */
    void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     *
     * @return returns route of vehicle
     */
    public LinkedList<Building> getRoute() {
        return this.route;
    }

    /**
     * Sets vehicle's route to given list
     * @param route linked list of buildings
     */
    void setRoute(LinkedList<Building> route) {
        this.route = route;
    }

    /**
     * Adds one given building to vehicle's route
     * @param b
     */
    private void addToRoute(Building b) {
        this.route.add(b);
    }

    /**
     * Method used by aircrafts to fly to nearest airport, should be replaced by Interface
     */
    public abstract void flyToNearest();

    /**
     * Shows right buttons and labels in Vehicle Inspector for any Vehicle
     */
    @Override
    void inspect() {
        WorldController.getVehicleInspector().setSelectedVehicle(this);
        WorldController.getVehicleInspector().getFrame().setVisible(true);
        WorldController.getBuildingInspector().getFrame().setVisible(false);
    }

    /**
     * Sets travelled distance to 0;
     */
    void setDistanceTravelled() {
        this.distanceTravelled = (float) 0;
    }

    /**
     *
     * @return returns max level of fuel
     */
    public float getMaxFuel() {
        return this.maxFuel;
    }

    /**
     * Sets max fuel to 100;
     */
    private void setMaxFuel() {
        this.maxFuel = (float) 100;
    }

    /**
     *
     * @return returns actual level of fuel
     */
    public float getActualFuel() {
        return this.actualFuel;
    }

    /**
     * Sets actual level of fuel to given float
     * @param actualFuel float
     */
    private void setActualFuel(float actualFuel) {
        this.actualFuel = actualFuel;
    }

    /**
     *
     * @return returns unique ID of vehicle
     */
    public String getUniqueId() {
        return this.uniqueId;
    }

    /**
     * Sets unique ID of vehicle to given string
     * @param uniqueId string
     */
    void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     *
     * @return returns list of passenger contained by Vehicle
     */
    public ConcurrentLinkedQueue<Passenger> getPassengerLinkedList() {
        return this.passengerLinkedList;
    }

    /**
     * Adds one given passenger to Vehicle's passengers list
     * @param passenger Passenger
     */
    public void addPassenger(Passenger passenger){
        this.passengerLinkedList.add(passenger);
    }

    /**
     * removes one given passenger from Vehicle's passenger list
     * @param passenger Passenger
     */
    public void removePassenger(Passenger passenger){
        this.passengerLinkedList.remove(passenger);
    }

    /**
     *
     * @return returns true if vehicle is in building, else returns false
     */
    public boolean isLanded() {
        return this.landed;
    }

    /**
     *
     * @return returns maximum count of possible vehicle's passengers
     */
    public int getMaxPassengerCount() {
        return this.maxPassengerCount;
    }

    /**
     * Sets maximum passenger count to given value
     * @param maxPassengerCount integer
     */
    public void setMaxPassengerCount(int maxPassengerCount) {
        this.maxPassengerCount = maxPassengerCount;
    }


}
