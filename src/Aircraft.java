import java.awt.geom.Point2D;

/**
 * Created by Tymek on 13.10.15.
 */
public abstract class Aircraft extends Vehicle {

    private int stuffCount;


    Aircraft() {
        this.stuffCount = 10;
    }

    @Override
    public void flyToNearest() {
        float distance;
        float min = Float.MAX_VALUE;
        Building nearest = null;
        for (int i = 0; i < WorldController.getMainMap().getAirportList().size(); i++) {
            distance = (float) Point2D.distance(this.getPosition().getX(),
                    this.getPosition().getY(),
                    WorldController.getMainMap().getAirportList().get(i).getPosition().getX(),
                    WorldController.getMainMap().getAirportList().get(i).getPosition().getY());
            if (distance < min) {
                min = distance;
                nearest = WorldController.getMainMap().getAirportList().get(i);
            }
            this.setDistanceTravelled();
            this.setNextDestination(nearest);
            this.getRoute().clear();
            this.getRoute().add(0, nearest);
            this.setMoving(false);
        }
    }


    @Override
    void inspect() {
        super.inspect();
        WorldController.getVehicleInspector().getCompanyNameLabel().setVisible(false);
        WorldController.getVehicleInspector().getCompanyValueLabel().setVisible(false);
        WorldController.getVehicleInspector().getEmergencyLandingButton().setVisible(true);
    }

    public int getStuffCount() {
        return this.stuffCount;
    }

    public void setStuffCount(int stuffCount) {
        this.stuffCount = stuffCount;
    }

    public Building getRandomConnected(){
        return MapConfig.getPassengerAirports()[MapConfig.randInt(0, MapConfig.getPassengerAirports().length - 1)];
    }

}
