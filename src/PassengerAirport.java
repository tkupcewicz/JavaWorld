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

    public PassengerAirport() {
        img = MapConfig.getPassAirportImg();
    }

    @Override
    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(
                MapConfig.getPassAirportImg(),
                (int) this.getPosition().getX() - MapConfig.getPassAirportImg().getWidth()/2,
                (int) this.getPosition().getY() - MapConfig.getPassAirportImg().getHeight()/2,
                WorldController.getWorldController());
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
