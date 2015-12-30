import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tymek on 30.12.2015.
 */
public class Crossroad extends Building {

    public Crossroad() {
        this.setConnections(new ArrayList<Position>());
    }

    @Override
    void drawImage() {

    }

    @Override
    BufferedImage getImage() {
        return null;
    }
}
