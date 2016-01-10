/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Military Aicraft class
 */
class MilitaryAircraft extends Aircraft {
    /**
     * Constructor which spawns Military Plane at given Position
     * @param position position
     */
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

    /**
     *
     * @return returns BufferedImage of Military Aicraft
     */
    @Override
    BufferedImage getImage() {
        return MapConfig.getMiliAircraftImg();
    }

    /**
     * Shows right buttons and labels in Vehicle Inspector for Military Aircraft
     */
    @Override
    public void inspect() {
        super.inspect();
        WorldController.getVehicleInspector().getSpawnPlaneButton().setVisible(false);
        WorldController.getVehicleInspector().getPassengerPane().setVisible(false);
        WorldController.getVehicleInspector().getPassengersLabel().setVisible(false);
        WorldController.getVehicleInspector().getActPassLabel().setVisible(false);
        WorldController.getVehicleInspector().getMaxPassLabel().setVisible(false);
        WorldController.getVehicleInspector().getActPassValueLabel().setVisible(false);
        WorldController.getVehicleInspector().getMaxPassValue().setVisible(false);
    }
}
