/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.image.BufferedImage;

public class PassengerAircraft extends Aircraft {

    public PassengerAircraft() {
        WorldController.getMainMap().addObjectToDraw(this);
        setCurrentBuilding(MapConfig.getPassengerAirports()[MapConfig.randInt(0,
                MapConfig.getPassengerAirports().length - 1)]);
        setPosition(getCurrentBuilding().getPosition().getX(), getCurrentBuilding().getPosition().getY());
        PassengerInfo passengerInfo = new PassengerInfo();
        setSpeed(MapConfig.randInt(MapConfig.getMinVehicleSpeed(), MapConfig.getMaxVehicleSpeed()));
        System.out.println("Plane spawned");
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
        refuel();
    }

    @Override
    public void inspect() {
        super.inspect();
        WorldController.getVehicleInspector().getSpawnPlaneButton().setVisible(false);
        WorldController.getVehicleInspector().getPassengerPane().setVisible(true);
        WorldController.getVehicleInspector().getPassengersLabel().setVisible(true);
    }
}
