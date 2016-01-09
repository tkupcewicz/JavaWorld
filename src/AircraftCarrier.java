/**
 * Created by Tymek on 13.10.15.
 */
import java.awt.image.BufferedImage;
import java.util.UUID;

public class AircraftCarrier extends Ship {

    public AircraftCarrier() {
        this.setUniqueId(UUID.randomUUID().toString());
        WorldController.getMainMap().addObjectToDraw(this);
        this.setCurrentBuilding(MapConfig.getHarbors()[MapConfig.randInt(0,
                MapConfig.getHarbors().length - 1)]);
        this.setPosition(this.getCurrentBuilding().getPosition().getX(), this.getCurrentBuilding().getPosition().getY());
        this.setSpeed(MapConfig.randInt(MapConfig.getMinVehicleSpeed(), MapConfig.getMaxVehicleSpeed()));
    }

    @Override
    public void inspect(){
        super.inspect();
        WorldController.getVehicleInspector().getSpawnPlaneButton().setVisible(true);
        WorldController.getVehicleInspector().getPassengerPane().setVisible(false);
        WorldController.getVehicleInspector().getPassengersLabel().setVisible(false);
    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getAircraftCarrierImg();
    }

    void spawnMiliPlane() {

    }


    @Override
    void moveTo() {

    }

    @Override
    public void flyToNearest() {

    }

}
