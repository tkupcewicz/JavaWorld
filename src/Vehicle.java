import com.sun.javafx.geom.Point2D;

import java.util.LinkedList;
import java.util.UUID;

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
    private float distanceTravelled;
    private boolean moving;

    Vehicle() {
        setUniqueId(UUID.randomUUID().toString());
        setMaxFuel();
        setActualFuel(getMaxFuel());
    }

    private float calculateXVel(float posX, float posY, float tarX, float tarY) {
        float angle = (float) atan2(tarY - posY, tarX - posX);
        return (float) (speed * Math.cos(angle));
    }

    private float calculateYVel(float posX, float posY, float tarX, float tarY) {
        float angle = (float) atan2(tarY - posY, tarX - posX);
        return (float) (speed * Math.sin(angle));
    }

    private void randomizeRoute(Building b) {
        Building tmp1 = b;
        for (int i = 0; i < MapConfig.randInt(MapConfig.getMinRouteLength(), MapConfig.getMaxRouteLength()); i++) {
            Building tmp2 = tmp1.getConnections().get(MapConfig.randInt(0, tmp1.getConnections().size() - 1));
            addToRoute(tmp2);
            tmp1 = tmp2;
        }
    }

    @Override
    public void run() {
        if (currentBuilding != null) {
            route = new LinkedList<>();
            randomizeRoute(currentBuilding);
            setNextDestination(route.get(0));
            route.remove(0);
        }
        float xVel = 0;
        float yVel = 0;
        float routeDistance = 0;
        distanceTravelled = 0;
        while (true) {
            if (!isMoving()) {
                Path temp = Path.calculatePath(getPosition(),
                        getNextDestination().getPosition(), -7);
                routeDistance = Point2D.distance(temp.getOrigin().getX(), temp.getOrigin().getY(),
                        temp.getDestination().getX(), temp.getDestination().getY());
                setPosition(temp.getDestination().getX(), temp.getDestination().getY());

                xVel = calculateXVel(temp.getDestination().getX(), temp.getDestination().getY(), temp.getOrigin().getX(), temp.getOrigin().getY());
                yVel = calculateYVel(temp.getDestination().getX(), temp.getDestination().getY(), temp.getOrigin().getX(), temp.getOrigin().getY());

                setMoving(true);
            } else {
                float tmpx = getPosition().getX();
                float tmpy = getPosition().getY();
                tmpx = tmpx + xVel * MapConfig.getVehiclesSpeed();
                tmpy = tmpy + yVel * MapConfig.getVehiclesSpeed();
                setActualFuel(getActualFuel() - 0.15f * MapConfig.getVehiclesSpeed());

                setPosition(tmpx, tmpy);
                distanceTravelled = (float) (distanceTravelled + Math.sqrt(Math.pow(xVel * MapConfig.getVehiclesSpeed(), 2) + Math.pow(yVel * MapConfig.getVehiclesSpeed(), 2)));

                if (distanceTravelled >= routeDistance) {
                    distanceTravelled = 0;
                    setMoving(false);
                    setPosition(nextDestination.getPosition());
                    //TUTAJ ARRIVE9
                    setCurrentBuilding(getNextDestination());
                    actualFuel = maxFuel;
                    if (route.size() == 1) {
                        setNextDestination(route.get(0));
                        route.remove(0);
                        randomizeRoute(nextDestination);
                    } else {
                        setNextDestination(route.get(0));
                        route.remove(0);
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
        return nextDestination;
    }

    void setNextDestination(Building nextDestination) {
        this.nextDestination = nextDestination;
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }

    Building getCurrentBuilding() {
        return currentBuilding;
    }

    void setCurrentBuilding(Building currentBuilding) {
        this.currentBuilding = currentBuilding;
    }

    private boolean isMoving() {
        return moving;
    }

    void setMoving(boolean moving) {
        this.moving = moving;
    }

    public LinkedList<Building> getRoute() {
        return route;
    }

    void setRoute(LinkedList<Building> route) {
        this.route = route;
    }

    private void addToRoute(Building b) {
        route.add(b);
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
        distanceTravelled = (float) 0;
    }

    public void refuel() {
        actualFuel = maxFuel;
    }

    public float getMaxFuel() {
        return maxFuel;
    }

    private void setMaxFuel() {
        maxFuel = (float) 100;
    }

    public float getActualFuel() {
        return actualFuel;
    }

    private void setActualFuel(float actualFuel) {
        this.actualFuel = actualFuel;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

}
