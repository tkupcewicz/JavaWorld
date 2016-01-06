/**
 * Created by Tymek on 13.10.15.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.UUID;

public class MilitaryAircraft extends Aircraft {

    private Weapon weaponType;

    public MilitaryAircraft(){
        WorldController.getMainMap().addObjectToDraw(this);
        this.setCurrentBuilding(MapConfig.getMilitaryAirports()[MapConfig.randInt(0,
                MapConfig.getMilitaryAirports().length - 1)]);
        this.setPosition(this.getCurrentBuilding().getPosition().getX(),
                this.getCurrentBuilding().getPosition().getY());
        this.setUniqueId(UUID.randomUUID().toString());
        this.weaponType = Weapon.getRandom();
    }

    @Override
    void drawImage(Graphics g) {

    }

    void goToNearest(){

    }

    @Override
    void moveTo() {

    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getMiliAircraftImg();
    }

}
