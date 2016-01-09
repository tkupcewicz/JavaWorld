/**
 * Created by Tymek on 29.10.15.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class PassengerAirport extends Airport {

    public PassengerAirport(int posX, int posY){
        this.setPosition(posX,posY);
        this.setConnections(new LinkedList());
        img = MapConfig.getPassAirportImg();
    }

    @Override
    void receiveAircraft() {

    }

    @Override
    void sendAircraft() {

    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getPassAirportImg();
    }
}
