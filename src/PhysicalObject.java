/**
 * Created by Tymek on 15.10.15.
 */
import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class PhysicalObject {

    protected BufferedImage img;
    private Position position;
    abstract void drawImage(Graphics g);
    public Position getPosition() {
        return position;
    }

    public void setPosition(float posX, float posY) {
        position = new Position(posX, posY);
    }

    public void setPosition(Position pos){
        position = new Position(pos.getX(), pos.getY());
    }

    abstract BufferedImage getImage();
}
