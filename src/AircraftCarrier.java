/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.image.BufferedImage;

/**
 * Type of Military Ship which spawns Military Aircrafts
 */
public class AircraftCarrier extends Ship {
    private final Weapon weaponType;

    /**
     * Constructor spawning Aicraft Carrier at random Harbor.
     * Sets random speed taken from MapConfig.
     */
    public AircraftCarrier() {
        WorldController.getMainMap().addObjectToDraw(this);
        this.setCurrentBuilding(MapConfig.getHarbors()[MapConfig.randInt(0,
                MapConfig.getHarbors().length - 1)]);
        this.setPosition(this.getCurrentBuilding().getPosition().getX(), this.getCurrentBuilding().getPosition().getY());
        this.setSpeed(MapConfig.randInt(MapConfig.getMinVehicleSpeed(), MapConfig.getMaxVehicleSpeed()));
        this.weaponType = Weapon.getRandom();
        this.setMaxPassengerCount(0);
    }

    /**
     * Shows right buttons and labels in Vehicle Inspector for AircraftCarrier
     */
    @Override
    public void inspect() {
        super.inspect();
        WorldController.getVehicleInspector().getSpawnPlaneButton().setVisible(true);
        WorldController.getVehicleInspector().getPassengerPane().setVisible(false);
        WorldController.getVehicleInspector().getPassengersLabel().setVisible(false);
        WorldController.getVehicleInspector().getCompanyNameLabel().setVisible(false);
        WorldController.getVehicleInspector().getCompanyValueLabel().setVisible(false);
        WorldController.getVehicleInspector().getActPassLabel().setVisible(false);
        WorldController.getVehicleInspector().getMaxPassLabel().setVisible(false);
        WorldController.getVehicleInspector().getActPassValueLabel().setVisible(false);
        WorldController.getVehicleInspector().getMaxPassValue().setVisible(false);
    }

    /**
     *
     * @return returns BufferedImage of AircraftCarrier
     */
    @Override
    BufferedImage getImage() {
        return MapConfig.getAircraftCarrierImg();
    }

    @Override
    public void flyToNearest() {

    }
}
