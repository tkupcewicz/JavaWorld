/**
 * Created by Tymek on 13.10.15.
 */
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.UUID;

public class MilitaryAircraft extends Aircraft {

    private Weapon weaponType;

    public MilitaryAircraft(Position position){
        super();
        this.setRoute(new LinkedList<>());
        WorldController.getMainMap().addObjectToDraw(this);
        this.setPosition(position);
        this.setUniqueId(UUID.randomUUID().toString());
        this.weaponType = Weapon.getRandom();
        this.setSpeed(MapConfig.randInt(MapConfig.getMinVehicleSpeed(), MapConfig.getMaxVehicleSpeed()));
        this.flyToNearest();
        this.weaponType = Weapon.getRandom();
    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getMiliAircraftImg();
    }

    @Override
    public void inspect(){
        super.inspect();
        WorldController.getVehicleInspector().getSpawnPlaneButton().setVisible(false);
        WorldController.getVehicleInspector().getPassengerPane().setVisible(false);
        WorldController.getVehicleInspector().getPassengersLabel().setVisible(false);
    }
}
