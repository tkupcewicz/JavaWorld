/**
 * Created by Tymek on 29.10.15.
 */
import javafx.geometry.Pos;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

public class MilitaryAirport extends Airport {

    public MilitaryAirport(int posX, int posY){
        this.setPosition(posX,posY);
        this.setConnections(new LinkedList());
    }

    public MilitaryAirport() {
    }

    @Override
    void drawImage(Graphics g) {

    }

    @Override
    void receiveAircraft() {

    }

    @Override
    void sendAircraft() {

    }
}
