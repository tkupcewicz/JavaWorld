import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tymek on 30.12.2015.
 */
public class Crossroad extends Building {

    public Crossroad(int posX, int posY) {
        this.setPosition(posX,posY);
        this.setConnections(new ArrayList<Position>());
    }

    public Crossroad() {
    }

    @Override
    void drawImage(Graphics g) {

    }

    @Override
    BufferedImage getImage() {
        return null;
    }
}
