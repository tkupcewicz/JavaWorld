/**
 * Created by Tymek on 15.10.15.
 */
import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class PhysicalObject {

    protected BufferedImage img;
    protected Graphics2D g2;
    private Position position;
    abstract void drawImage(Graphics g);
    abstract BufferedImage getImage();

    public Position getPosition() {
        return position;
    }

    public void setPosition(int posX, int posY) {
        position = new Position(posX, posY);
    }
}
