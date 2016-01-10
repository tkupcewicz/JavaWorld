/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.UUID;

class MilitaryAircraft extends Aircraft {

    public MilitaryAircraft(Position position) {
        this.setRoute(new LinkedList<>());
        WorldController.getMainMap().addObjectToDraw(this);
        this.setPosition(position);
        this.setUniqueId(UUID.randomUUID().toString());
        Weapon weaponType = Weapon.getRandom();
        this.setSpeed(MapConfig.randInt(MapConfig.getMinVehicleSpeed(), MapConfig.getMaxVehicleSpeed()));
        this.flyToNearest();
        this.setMaxPassengerCount(0);

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
