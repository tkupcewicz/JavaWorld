/**
 * Created by Tymek on 15.10.15.
 */

import java.awt.*;
import java.awt.image.BufferedImage;


abstract class PhysicalObject {

    BufferedImage img;
    private Position position;

    void drawImage(Graphics g) {
        g.drawImage(
                getImage(),
                (int) getPosition().getX() - getImage().getWidth() / 2,
                (int) getPosition().getY() - getImage().getHeight() / 2,
                WorldController.getWorldController());
    }

    public Position getPosition() {
        return position;
    }

    void setPosition(Position pos) {
        position = new Position(pos.getX(), pos.getY());
    }

    void setPosition(float posX, float posY) {
        position = new Position(posX, posY);
    }

    abstract BufferedImage getImage();

    abstract void inspect();
}
