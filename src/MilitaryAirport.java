/**
 * Created by Tymek on 29.10.15.
 */

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class MilitaryAirport extends Airport {

    public MilitaryAirport(int posX, int posY) {
        setPosition(posX, posY);
        setConnections(new LinkedList());
    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getMiliAirportImg();
    }
}
