/**
 * Created by Tymek on 29.10.15.
 */

import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class PassengerAirport extends Airport {

    public PassengerAirport(int posX, int posY) {
        setPosition(posX, posY);
        setConnections(new LinkedList());
        img = MapConfig.getPassAirportImg();
    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getPassAirportImg();
    }
}
