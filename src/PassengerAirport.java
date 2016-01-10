/**
 * Created by Tymek on 29.10.15.
 */

import java.awt.image.BufferedImage;
import java.util.LinkedList;


/**
 * Passenger Airport class
 */
public class PassengerAirport extends Airport {

    /**
     * Constructor which creates Passenger Airport at given coordinates X and Y
     * @param posX int
     * @param posY int
     */
    public PassengerAirport(int posX, int posY) {
        this.setPosition(posX, posY);
        this.setConnections(new LinkedList());
        this.img = MapConfig.getPassAirportImg();
    }

    /**
     *
     * @return returns image of Passenger Airport from Map Config
     */
    @Override
    BufferedImage getImage() {
        return MapConfig.getPassAirportImg();
    }

    /**
     *
     * @return returns random Building from whole net of connections in which this Airport is
     */
    @Override
    public Building getRandomConnected(){
        Building dest = this;
        while(dest.equals(this)){
            dest = MapConfig.getPassengerAirports()[MapConfig.randInt(0, MapConfig.getPassengerAirports().length - 1)];
        }
        return dest;
    }
}
