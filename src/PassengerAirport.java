/**
 * Created by Tymek on 29.10.15.
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;


public class PassengerAirport extends Airport {

    public PassengerAirport(int posX, int posY){
        this.setPosition(posX,posY);
    }

    public PassengerAirport() {
        try {
            img = ImageIO.read(this.getClass().getResource("images/passAirport.png"));
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    @Override
    Image getImage() {
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
