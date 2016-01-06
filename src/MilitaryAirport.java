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

    public MilitaryAirport() {
    }

    @Override
    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(
                MapConfig.getMiliAirportImg(),
                (int) this.getPosition().getX() - MapConfig.getMiliAirportImg().getWidth()/2,
                (int) this.getPosition().getY() - MapConfig.getMiliAirportImg().getHeight()/2,
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
        return MapConfig.getMiliAirportImg();
    }
}
