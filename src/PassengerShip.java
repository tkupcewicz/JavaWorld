/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.image.BufferedImage;

public class PassengerShip extends Ship {
    private PassengerInfo passengerInfo;
    private final String companyName;

    public PassengerShip() {
        this.companyName = "White Star Line";
        WorldController.getMainMap().addObjectToDraw(this);
        this.setCurrentBuilding(MapConfig.getHarbors()[MapConfig.randInt(0,
                MapConfig.getHarbors().length - 1)]);
        this.setPosition(this.getCurrentBuilding().getPosition().getX(), this.getCurrentBuilding().getPosition().getY());
        this.setSpeed(MapConfig.randInt(MapConfig.getMinVehicleSpeed(), MapConfig.getMaxVehicleSpeed()));
    }


    @Override
    BufferedImage getImage() {
        return MapConfig.getPassengerShipImg();
    }

    @Override
    public void flyToNearest() {

    }

    @Override
    public void inspect() {
        super.inspect();
        WorldController.getVehicleInspector().getSpawnPlaneButton().setVisible(false);
        WorldController.getVehicleInspector().getPassengerPane().setVisible(true);
        WorldController.getVehicleInspector().getPassengersLabel().setVisible(true);
        WorldController.getVehicleInspector().getCompanyNameLabel().setVisible(true);
        WorldController.getVehicleInspector().getCompanyValueLabel().setVisible(true);
        WorldController.getVehicleInspector().getCompanyValueLabel().setText(this.companyName);
    }
}
