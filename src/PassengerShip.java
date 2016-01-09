/**
 * Created by Tymek on 13.10.15.
 */
import java.awt.image.BufferedImage;
import java.util.UUID;

public class PassengerShip extends Ship {
    private PassengerInfo passengerInfo ;
    private String companyName;

    public PassengerShip() {
        this.setUniqueId(UUID.randomUUID().toString());
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
    void moveTo() {

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
    }
}
