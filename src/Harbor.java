/**
 * Created by Tymek on 15.10.15.
 */

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Harbor extends Building {

    public Harbor(int posX, int posY) {
        this.setPosition(posX, posY);
        this.setConnections(new LinkedList());
        this.img = MapConfig.getHarborImg();
    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getHarborImg();
    }

    @Override
    public void inspect() {
        super.inspect();
        WorldController.getBuildingInspector().setVehicleTypeLabel("Ships: ");
    }

    @Override
    public Building getRandomConnected(){
        Building dest = this;
        while(dest.equals(this)){
            dest = MapConfig.getHarbors()[MapConfig.randInt(0, MapConfig.getHarbors().length - 1)];
        }
        return dest;
    }
}
