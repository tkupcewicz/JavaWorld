/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.UUID;

class MilitaryAircraft extends Aircraft {

    public MilitaryAircraft(Position position) {
        setRoute(new LinkedList<>());
        WorldController.getMainMap().addObjectToDraw(this);
        setPosition(position);
        setUniqueId(UUID.randomUUID().toString());
        Weapon weaponType = Weapon.getRandom();
        setSpeed(MapConfig.randInt(MapConfig.getMinVehicleSpeed(), MapConfig.getMaxVehicleSpeed()));
        flyToNearest();
        weaponType = Weapon.getRandom();
    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getMiliAircraftImg();
    }

    @Override
    public void inspect() {
        super.inspect();
        WorldController.getVehicleInspector().getSpawnPlaneButton().setVisible(false);
        WorldController.getVehicleInspector().getPassengerPane().setVisible(false);
        WorldController.getVehicleInspector().getPassengersLabel().setVisible(false);
    }
}
