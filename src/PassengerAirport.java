/**
 * Created by Tymek on 29.10.15.
 */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class PassengerAirport extends Airport {

    public PassengerAirport(int posX, int posY){
        this.setPosition(posX,posY);
        this.setConnections(new ArrayList<Position>());
    }

    public PassengerAirport() {
        img = MapConfig.getPassAirportImg();

    }

    @Override
    BufferedImage getImage() {
        return img;
    }

    @Override
    void drawImage() {

    }

    @Override
    void receiveAircraft() {

    }

    @Override
    void sendAircraft() {

    }
}
