/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.image.BufferedImage;

/**
 * Passenger Ship class
 */
public class PassengerShip extends Ship {
    private PassengerInfo passengerInfo;
    private final String companyName;

    /**
     * Constructor which sets Company Name to Titanic's company name.
     * Also sets starting Harbor and other parameters.
     */
    public PassengerShip() {
        this.companyName = "White Star Line";
        WorldController.getMainMap().addObjectToDraw(this);
        this.setCurrentBuilding(MapConfig.getHarbors()[MapConfig.randInt(0,
                MapConfig.getHarbors().length - 1)]);
        this.setPosition(this.getCurrentBuilding().getPosition().getX(), this.getCurrentBuilding().getPosition().getY());
        this.setSpeed(MapConfig.randInt(MapConfig.getMinVehicleSpeed(), MapConfig.getMaxVehicleSpeed()));
        this.setMaxPassengerCount(MapConfig.randInt(MapConfig.getMinPassengerCount(),
                MapConfig.getMaxPassengerCount()));
        for (int i = 0; i < this.getMaxPassengerCount(); i++) {
            WorldController.getMainMap().spawnPassenger();
        }
    }

    /**
     *
     * @return returns BufferedImage of Passenger Ship
     */
    @Override
    BufferedImage getImage() {
        return MapConfig.getPassengerShipImg();
    }

    /**
     * Empty method replacing lack of time to write Interface
     */
    @Override
    public void flyToNearest() {

    }

    /**
     * Shows right buttons and labels in Vehicle Inspector for Passenger Ship
     */
    @Override
    public void inspect() {
        super.inspect();
        WorldController.getVehicleInspector().getSpawnPlaneButton().setVisible(false);
        WorldController.getVehicleInspector().getPassengerPane().setVisible(true);
        WorldController.getVehicleInspector().getPassengersLabel().setVisible(true);
        WorldController.getVehicleInspector().getCompanyNameLabel().setVisible(true);
        WorldController.getVehicleInspector().getCompanyValueLabel().setVisible(true);
        WorldController.getVehicleInspector().getCompanyValueLabel().setText(this.companyName);
        WorldController.getVehicleInspector().getActPassLabel().setVisible(true);
        WorldController.getVehicleInspector().getMaxPassLabel().setVisible(true);
        WorldController.getVehicleInspector().getActPassValueLabel().setVisible(true);
        WorldController.getVehicleInspector().getMaxPassValue().setVisible(true);
    }
}
