/**
 * Created by Tymek on 29.10.15.
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;


public class PassengerAirport extends Airport {

    public PassengerAirport(int posX, int posY){
        this.setPosition(posX,posY);
        this.setConnections(new ArrayList<Position>());
        img = MapConfig.getPassAirportImg();
    }

    public PassengerAirport() {
        img = MapConfig.getPassAirportImg();
    }

    @Override
    BufferedImage getImage() {
        return img;
    }

    @Override
    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(
                this.getImage(),
                this.getPosition().getX() - MapConfig.getPassAirportImg().getWidth()/2,
                this.getPosition().getY() - MapConfig.getPassAirportImg().getHeight()/2,
                WorldController.worldController);
    }

    @Override
    void receiveAircraft() {

    }

    @Override
    void sendAircraft() {

    }
}
