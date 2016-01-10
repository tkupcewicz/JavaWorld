/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.image.BufferedImage;
import java.util.UUID;

public class PassengerAircraft extends Aircraft {
    private PassengerInfo passengerInfo;

    public PassengerAircraft() {
        super();
        WorldController.getMainMap().addObjectToDraw(this);
        this.setCurrentBuilding(MapConfig.getPassengerAirports()[MapConfig.randInt(0,
                MapConfig.getPassengerAirports().length - 1)]);
        this.setPosition(this.getCurrentBuilding().getPosition().getX(), this.getCurrentBuilding().getPosition().getY());
        this.passengerInfo = new PassengerInfo();
        this.setSpeed(MapConfig.randInt(MapConfig.getMinVehicleSpeed(), MapConfig.getMaxVehicleSpeed()));
        System.out.println("Plane spawned");
    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getPassAircraftImg();
    }

    void getPassengers() {

    }

    void leavePassengers() {

    }

    public void refuel() {

    }

    void leaveAirport() {

    }

    @Override
    public void arrive(){
        this.refuel();
    }

    @Override
    public void inspect() {
        super.inspect();
        WorldController.getVehicleInspector().getSpawnPlaneButton().setVisible(false);
        WorldController.getVehicleInspector().getPassengerPane().setVisible(true);
        WorldController.getVehicleInspector().getPassengersLabel().setVisible(true);
    }
}
