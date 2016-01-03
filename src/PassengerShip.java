/**
 * Created by Tymek on 13.10.15.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class PassengerShip extends Ship {
    private PassengerInfo passengerInfo ;
    private String companyName;

    @Override
    void drawImage(Graphics g) {

    }

    @Override
    BufferedImage getImage() {
        return null;
    }

    @Override
    void moveTo() {

    }

    @Override
    LinkedList<Building> randomizeRoute() {
        return null;
    }
}
