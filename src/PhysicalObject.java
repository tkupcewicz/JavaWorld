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
                this.getImage(),
                (int) this.getPosition().getX() - this.getImage().getWidth() / 2,
                (int) this.getPosition().getY() - this.getImage().getHeight() / 2,
                WorldController.getWorldController());
    }

    public Position getPosition() {
        return this.position;
    }

    void setPosition(Position pos) {
        this.position = new Position(pos.getX(), pos.getY());
    }

    void setPosition(float posX, float posY) {
        this.position = new Position(posX, posY);
    }

    abstract BufferedImage getImage();

    abstract void inspect();
}
