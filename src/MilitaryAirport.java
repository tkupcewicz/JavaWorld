/**
 * Created by Tymek on 29.10.15.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class MilitaryAirport extends Airport {

    public MilitaryAirport(int posX, int posY){
        this.setPosition(posX,posY);
        this.setConnections(new LinkedList());
    }

    @Override
    void receiveAircraft() {

    }

    @Override
    void sendAircraft() {

    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getMiliAirportImg();
    }
}
