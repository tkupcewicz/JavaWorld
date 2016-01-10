import com.sun.javafx.geom.Point2D;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import static java.lang.Math.atan2;

/**
 * Created by Tymek on 13.10.15.
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


    Vehicle() {
        this.passengerLinkedList = new ConcurrentLinkedQueue<>();
        this.setUniqueId(UUID.randomUUID().toString());
        this.setMaxFuel();
        this.setActualFuel(this.getMaxFuel());
    }

    private float calculateXVel(float posX, float posY, float tarX, float tarY) {
        float angle = (float) atan2(tarY - posY, tarX - posX);
        return (float) (this.speed * Math.cos(angle));
    }

    private float calculateYVel(float posX, float posY, float tarX, float tarY) {
        float angle = (float) atan2(tarY - posY, tarX - posX);
        return (float) (this.speed * Math.sin(angle));
    }

    private void randomizeRoute(Building b) {
        Building tmp1 = b;
        for (int i = 0; i < MapConfig.randInt(MapConfig.getMinRouteLength(), MapConfig.getMaxRouteLength()); i++) {
            Building tmp2 = tmp1.getConnections().get(MapConfig.randInt(0, tmp1.getConnections().size() - 1));
            this.addToRoute(tmp2);
            tmp1 = tmp2;
        }
    }

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
                //noinspection UnqualifiedMethodAccess,UnqualifiedMethodAccess
                Path temp = Path.calculatePath(this.getPosition(),
                        this.getNextDestination().getPosition(), -7);
                routeDistance = Point2D.distance(temp.getOrigin().getX(), temp.getOrigin().getY(),
                        temp.getDestination().getX(), temp.getDestination().getY());
                this.setPosition(temp.getDestination().getX(), temp.getDestination().getY());

                xVel = this.calculateXVel(temp.getDestination().getX(), temp.getDestination().getY(), temp.getOrigin().getX(), temp.getOrigin().getY());
                yVel = this.calculateYVel(temp.getDestination().getX(), temp.getDestination().getY(), temp.getOrigin().getX(), temp.getOrigin().getY());

                this.setMoving(true);
            } else {
                //noinspection UnqualifiedMethodAccess
                float tmpx = this.getPosition().getX();
                //noinspection UnqualifiedMethodAccess
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

    public Building getNextDestination() {
        return this.nextDestination;
    }

    void setNextDestination(Building nextDestination) {
        this.nextDestination = nextDestination;
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }

    Building getCurrentBuilding() {
        return this.currentBuilding;
    }

    void setCurrentBuilding(Building currentBuilding) {
        this.currentBuilding = currentBuilding;
    }

    private boolean isMoving() {
        return this.moving;
    }

    void setMoving(boolean moving) {
        this.moving = moving;
    }

    public LinkedList<Building> getRoute() {
        return this.route;
    }

    void setRoute(LinkedList<Building> route) {
        this.route = route;
    }

    private void addToRoute(Building b) {
        this.route.add(b);
    }

    public void arrive() {

    }

    public abstract void flyToNearest();

    @Override
    void inspect() {
        WorldController.getVehicleInspector().setSelectedVehicle(this);
        WorldController.getVehicleInspector().getFrame().setVisible(true);
        WorldController.getBuildingInspector().getFrame().setVisible(false);
    }

    void setDistanceTravelled() {
        this.distanceTravelled = (float) 0;
    }

    public void refuel() {
        this.actualFuel = this.maxFuel;
    }

    public float getMaxFuel() {
        return this.maxFuel;
    }

    private void setMaxFuel() {
        this.maxFuel = (float) 100;
    }

    public float getActualFuel() {
        return this.actualFuel;
    }

    private void setActualFuel(float actualFuel) {
        this.actualFuel = actualFuel;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public ConcurrentLinkedQueue<Passenger> getPassengerLinkedList() {
        return this.passengerLinkedList;
    }

    public void addPassenger(Passenger passenger){
        this.passengerLinkedList.add(passenger);
    }

    public void removePassenger(Passenger passenger){
        this.passengerLinkedList.remove(passenger);
    }

    public boolean isLanded() {
        return this.landed;
    }

    public int getMaxPassengerCount() {
        return this.maxPassengerCount;
    }

    public void setMaxPassengerCount(int maxPassengerCount) {
        this.maxPassengerCount = maxPassengerCount;
    }
}
