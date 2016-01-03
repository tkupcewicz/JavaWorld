import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tymek on 30.12.2015.
 */
public class Crossroad extends Building {

    public Crossroad(int posX, int posY) {
        this.setPosition(posX,posY);
        img = MapConfig.getCrossroadImg();
    }

    public Crossroad() {
    }

    @Override
    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(
                MapConfig.getCrossroadImg(),
                this.getPosition().getX() - MapConfig.getCrossroadImg().getWidth()/2,
                this.getPosition().getY() - MapConfig.getCrossroadImg().getHeight()/2,
                WorldController.getWorldController());
        System.out.println("Drawing crossroad at " + this.getPosition().toString());
    }

}
