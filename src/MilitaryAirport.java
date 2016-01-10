/**
 * Created by Tymek on 29.10.15.
 */

import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Military Airport class
 */
public class MilitaryAirport extends Airport {

    /**
     * Creates Military Airport at given coordinates X and Y
     * @param posX integer
     * @param posY integer
     */
    public MilitaryAirport(int posX, int posY) {
        this.setPosition(posX, posY);
        this.setConnections(new LinkedList());
    }

    /**
     *
     * @return returns image of Military Aiport from Map Config
     */
    @Override
    BufferedImage getImage() {
        return MapConfig.getMiliAirportImg();
    }

    /**
     * Method which is created by lack of time to create interfaces
     * @return retruns null
     */
    @Override
    public Building getRandomConnected(){
        return null;
    }
}
