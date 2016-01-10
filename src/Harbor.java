/**
 * Created by Tymek on 15.10.15.
 */

import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Harbor class
 */
public class Harbor extends Building {
    /**
     * Constructor of Harbor at given position x and y
     * @param posX position
     * @param posY position
     */
    public Harbor(int posX, int posY) {
        this.setPosition(posX, posY);
        this.setConnections(new LinkedList());
        this.img = MapConfig.getHarborImg();
    }

    /**
     *
     * @return returns image of Harbor
     */
    @Override
    BufferedImage getImage() {
        return MapConfig.getHarborImg();
    }

    /**
     * Shows right buttons and labels in Building Inspector for Harbor
     */
    @Override
    public void inspect() {
        super.inspect();
        WorldController.getBuildingInspector().setVehicleTypeLabel("Ships: ");
    }

    /**
     *
     * @return returns random Building from whole net of connections
     */
    @Override
    public Building getRandomConnected(){
        Building dest = this;
        while(dest.equals(this)){
            dest = MapConfig.getHarbors()[MapConfig.randInt(0, MapConfig.getHarbors().length - 1)];
        }
        return dest;
    }
}
