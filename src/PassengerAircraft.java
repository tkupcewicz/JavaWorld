/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.image.BufferedImage;

public class PassengerAircraft extends Aircraft {

    public PassengerAircraft() {
        WorldController.getMainMap().addObjectToDraw(this);
        this.setCurrentBuilding(MapConfig.getPassengerAirports()[MapConfig.randInt(0,
                MapConfig.getPassengerAirports().length - 1)]);
        this.setPosition(this.getCurrentBuilding().getPosition().getX(), this.getCurrentBuilding().getPosition().getY());
        PassengerInfo passengerInfo = new PassengerInfo();
        this.setSpeed(MapConfig.randInt(MapConfig.getMinVehicleSpeed(), MapConfig.getMaxVehicleSpeed()));
        System.out.println("Plane spawned");
        this.setMaxPassengerCount(MapConfig.randInt(MapConfig.getMinPassengerCount(),
                MapConfig.getMaxPassengerCount()));
    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getPassAircraftImg();
    }

    @Override
    public void refuel() {

    }

    @Override
    public void arrive() {
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
