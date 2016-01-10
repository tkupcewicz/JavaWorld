/**
 * Created by Tymek on 29.10.15.
 */

import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class PassengerAirport extends Airport {

    public PassengerAirport(int posX, int posY) {
        this.setPosition(posX, posY);
        this.setConnections(new LinkedList());
        this.img = MapConfig.getPassAirportImg();
    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getPassAirportImg();
    }

    @Override
    public Building getRandomConnected(){
        Building dest = this;
        while(dest.equals(this)){
            dest = MapConfig.getPassengerAirports()[MapConfig.randInt(0, MapConfig.getPassengerAirports().length - 1)];
        }
        return dest;
    }
}
