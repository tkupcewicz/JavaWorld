/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.image.BufferedImage;

/**
 * Passenger Aircraft class
 */
public class PassengerAircraft extends Aircraft {
    /**
     * Constructor which randoms first Aircraft airport and other parameters
     */
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
        for (int i = 0; i < this.getMaxPassengerCount(); i++) {
            WorldController.getMainMap().spawnPassenger();
        }
    }

    /**
     *
     * @return returns BufferedImage of Passenger Aircraft
     */
    @Override
    BufferedImage getImage() {
        return MapConfig.getPassAircraftImg();
    }
    /**
     * Shows right buttons and labels in Vehicle Inspector for Passenger Aircraft
     */
    @Override
    public void inspect() {
        super.inspect();
        WorldController.getVehicleInspector().getSpawnPlaneButton().setVisible(false);
        WorldController.getVehicleInspector().getPassengerPane().setVisible(true);
        WorldController.getVehicleInspector().getPassengersLabel().setVisible(true);
        WorldController.getVehicleInspector().getActPassLabel().setVisible(true);
        WorldController.getVehicleInspector().getMaxPassLabel().setVisible(true);
        WorldController.getVehicleInspector().getActPassValueLabel().setVisible(true);
        WorldController.getVehicleInspector().getMaxPassValue().setVisible(true);
    }
}
