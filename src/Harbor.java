/**
 * Created by Tymek on 15.10.15.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Harbor extends Building {

    public Harbor(int posX, int posY) {
        this.setPosition(posX,posY);
        this.setConnections(new LinkedList());
        img = MapConfig.getHarborImg();
    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getHarborImg();
    }
}
