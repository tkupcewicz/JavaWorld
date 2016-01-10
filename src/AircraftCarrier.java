/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.image.BufferedImage;

public class AircraftCarrier extends Ship {
    private final Weapon weaponType;

    public AircraftCarrier() {
        WorldController.getMainMap().addObjectToDraw(this);
        this.setCurrentBuilding(MapConfig.getHarbors()[MapConfig.randInt(0,
                MapConfig.getHarbors().length - 1)]);
        this.setPosition(this.getCurrentBuilding().getPosition().getX(), this.getCurrentBuilding().getPosition().getY());
        this.setSpeed(MapConfig.randInt(MapConfig.getMinVehicleSpeed(), MapConfig.getMaxVehicleSpeed()));
        this.weaponType = Weapon.getRandom();
    }

    @Override
    public void inspect() {
        super.inspect();
        WorldController.getVehicleInspector().getSpawnPlaneButton().setVisible(true);
        WorldController.getVehicleInspector().getPassengerPane().setVisible(false);
        WorldController.getVehicleInspector().getPassengersLabel().setVisible(false);
        WorldController.getVehicleInspector().getCompanyNameLabel().setVisible(false);
        WorldController.getVehicleInspector().getCompanyValueLabel().setVisible(false);
    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getAircraftCarrierImg();
    }

    @Override
    public void flyToNearest() {

    }

    public Weapon getWeaponType() {
        return this.weaponType;
    }
}
