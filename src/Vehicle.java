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

    public Vehicle() {
        this.setUniqueId(UUID.randomUUID().toString());
        this.setMaxFuel(100);
        this.setActualFuel(this.getMaxFuel());
    }

    float calculateXVel(float posX, float posY, float tarX, float tarY){
        float angle = (float) atan2(tarY-posY, tarX-posX);
        float xVel = (float) (speed * Math.cos(angle));
        return xVel;
    }
    float calculateYVel(float posX, float posY, float tarX, float tarY){
        float angle = (float) atan2(tarY-posY, tarX-posX);
        float yVel = (float) (speed * Math.sin(angle));
        return yVel;
    }

     public void randomizeRoute(Building b){
        Building tmp1 = b;
        for (int i = 0; i < MapConfig.randInt(MapConfig.getMinRouteLength(), MapConfig.getMaxRouteLength()); i++) {
            Building tmp2 = tmp1.getConnections().get(MapConfig.randInt(0, tmp1.getConnections().size() - 1));
            addToRoute(tmp2);
            tmp1 = tmp2;
        }
    }

    public void run() {
        if(currentBuilding != null){
            this.route = new LinkedList<>();
            this.randomizeRoute(this.currentBuilding);
            this.setNextDestination(route.get(0));
            this.route.remove(0);
        }
        float xVel = 0;
        float yVel = 0;
        float routeDistance = 0;
        distanceTravelled = 0;
        while(true) {
            if(!this.isMoving()){
                Path temp = Path.calculatePath(this.getPosition(),
                        this.getNextDestination().getPosition(), -7);
                routeDistance = Point2D.distance(temp.getOrigin().getX(), temp.getOrigin().getY(),
                        temp.getDestination().getX(), temp.getDestination().getY());
                this.setPosition(temp.getDestination().getX(), temp.getDestination().getY());

                xVel = calculateXVel(temp.getDestination().getX(), temp.getDestination().getY(), temp.getOrigin().getX(), temp.getOrigin().getY());
                yVel = calculateYVel(temp.getDestination().getX(), temp.getDestination().getY(), temp.getOrigin().getX(), temp.getOrigin().getY());

                this.setMoving(true);
            }
            else{
                float tmpx = this.getPosition().getX();
                float tmpy = this.getPosition().getY();
                tmpx = tmpx + xVel * MapConfig.getVehiclesSpeed();
                tmpy = tmpy + yVel * MapConfig.getVehiclesSpeed();
                this.setActualFuel(this.getActualFuel() - 0.15f * MapConfig.getVehiclesSpeed());

                this.setPosition(tmpx, tmpy);
                distanceTravelled = (float) (distanceTravelled + Math.sqrt(Math.pow(xVel * MapConfig.getVehiclesSpeed(), 2) + Math.pow(yVel * MapConfig.getVehiclesSpeed(), 2)));

                if(distanceTravelled >= routeDistance){
                    distanceTravelled = 0;
                    this.setMoving(false);
                    this.setPosition(this.nextDestination.getPosition());
                    //TUTAJ ARRIVE9
                    this.setCurrentBuilding(this.getNextDestination());
                    this.actualFuel = this.maxFuel;
                    if(this.route.size() == 1){
                        this.setNextDestination(this.route.get(0));
                        this.route.remove(0);
                        this.randomizeRoute(this.nextDestination);
                    }
                    else{
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
        return nextDestination;
    }

    public void setNextDestination(Building nextDestination) {
        this.nextDestination = nextDestination;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Building getCurrentBuilding() {
        return currentBuilding;
    }

    public void setCurrentBuilding(Building currentBuilding) {
        this.currentBuilding = currentBuilding;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public LinkedList<Building> getRoute() {
        return route;
    }

    public void addToRoute(Building b){
        route.add(b);
    }

    public void arrive(){

    }

    public void depart(){

    }

    public abstract void flyToNearest();

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void inspect(){
        WorldController.getVehicleInspector().setSelectedVehicle(this);
        WorldController.getVehicleInspector().getFrame().setVisible(true);
        WorldController.getBuildingInspector().getFrame().setVisible(false);
    }

    public void setDistanceTravelled(float distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public void setRoute(LinkedList<Building> route) {
        this.route = route;
    }

    public void refuel(){
        this.actualFuel = this.maxFuel;
    }

    public float getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(float maxFuel) {
        this.maxFuel = maxFuel;
    }

    public float getActualFuel() {
        return actualFuel;
    }

    public void setActualFuel(float actualFuel) {
        this.actualFuel = actualFuel;
    }

    public String getUniqueId() {
        return uniqueId;
    }

}
