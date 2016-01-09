import java.awt.geom.Point2D;

/**
 * Created by Tymek on 13.10.15.
 */
public abstract class Aircraft extends Vehicle{
    private float maxFuel;
    private float actualFuel;
    private int stuffCount;

    public void refuel(){
        this.actualFuel = this.maxFuel;
    }

    public Aircraft(){

    }
    
    public void flyToNearest(){
        float distance;
        float min = Float.MAX_VALUE;
        Building nearest = null;
        for (int i = 0; i < WorldController.getMainMap().getAirportList().size(); i++) {
            distance = (float) Point2D.distance(this.getPosition().getX(),
                    this.getPosition().getY(),
                    WorldController.getMainMap().getAirportList().get(i).getPosition().getX(),
                    WorldController.getMainMap().getAirportList().get(i).getPosition().getY());
            if (distance < min){
                min = distance;
                nearest = WorldController.getMainMap().getAirportList().get(i);
            }
            this.setDistanceTravelled(0);
            this.setNextDestination(nearest);
            this.getRoute().clear();
            this.getRoute().add(0,nearest);
            this.setMoving(false);
        }
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

    public int getStuffCount() {
        return stuffCount;
    }

    public void setStuffCount(int stuffCount) {
        this.stuffCount = stuffCount;
    }

}
