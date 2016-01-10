/**
 * Created by Tymek on 15.10.15.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Main abstract class of every object which is renderable and has position.
 */
abstract class PhysicalObject implements Serializable{

    BufferedImage img;
    private Position position;

    /**
     * Draws image at World Controller of given image
     * @param g image
     */
    void drawImage(Graphics g) {
        g.drawImage(
                this.getImage(),
                (int) this.getPosition().getX() - this.getImage().getWidth() / 2,
                (int) this.getPosition().getY() - this.getImage().getHeight() / 2,
                WorldController.getWorldController());
    }

    /**
     *
     * @return returns position of object
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Sets position of object to given position
     * @param pos position
     */
    void setPosition(Position pos) {
        this.position = new Position(pos.getX(), pos.getY());
    }

    /**
     * Sets position of object to given coordinates x and y
     * @param posX integer
     * @param posY integer
     */
    void setPosition(float posX, float posY) {
        this.position = new Position(posX, posY);
    }

    abstract BufferedImage getImage();

    abstract void inspect();
}
