/**
 * Created by Tymek on 29.10.15.
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class PassengerAirport extends Airport {

    public PassengerAirport(int posX, int posY){
        this.setPosition(posX,posY);
        this.setConnections(new LinkedList());
        img = MapConfig.getPassAirportImg();
    }

    public PassengerAirport() {
        img = MapConfig.getPassAirportImg();
    }

    @Override
    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(
                MapConfig.getPassAirportImg(),
                this.getPosition().getX() - MapConfig.getPassAirportImg().getWidth()/2,
                this.getPosition().getY() - MapConfig.getPassAirportImg().getHeight()/2,
                WorldController.getWorldController());
        System.out.println("Drawing airport");
    }

    @Override
    void receiveAircraft() {

    }

    @Override
    void sendAircraft() {

    }
}
