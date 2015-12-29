/**
 * Created by Tymek on 15.10.15.
 */
import java.awt.Image;
import java.awt.Graphics2D;


public abstract class PhysicalObject {

    protected Image img;
    protected Graphics2D g2;
    private Position position;
    abstract void drawImage();
    abstract Image getImage();

    public Position getPosition() {
        return position;
    }

    public void setPosition(int posX, int posY) {
        position = new Position(posX, posY);
    }
}
