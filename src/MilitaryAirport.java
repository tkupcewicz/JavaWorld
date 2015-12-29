/**
 * Created by Tymek on 29.10.15.
 */
import java.awt.*;

public class MilitaryAirport extends Airport {

    public MilitaryAirport(int posX, int posY){
        this.setPosition(posX,posY);
    }

    public MilitaryAirport() {
    }

    @Override
    Image getImage() {

        return null;
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
