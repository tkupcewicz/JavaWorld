/**
 * Created by Tymek on 15.10.15.
 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class Path extends PhysicalObject {
    private Position destination;
    private Position origin;

    public Path() {

    }

    public Path(Position a, Position b) {
        destination = a;
        origin = b;
    }

    public static Path calculatePath(Position A, Position B, int pathWidth) {
        float xA = A.getX();
        float yA = A.getY();
        float xB = B.getX();
        float yB = B.getY();
        double d = Math.sqrt(Math.pow(xA - xB, 2) + Math.pow(yA - yB, 2));

        double xAp = xA + pathWidth * (yB - yA) / d;
        double yAp = yA + pathWidth * (xA - xB) / d;
        double xBp = xB + pathWidth * (yB - yA) / d;
        double yBp = yB + pathWidth * (xA - xB) / d;
        Position aPrim = new Position((int) Math.round(xAp), (int) Math.round(yAp));
        Position bPrim = new Position((int) Math.round(xBp), (int) Math.round(yBp));
        return new Path(aPrim, bPrim);
    }

    public Position getDestination() {
        return destination;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }

    public Position getOrigin() {
        return origin;
    }

    public void setOrigin(Position origin) {
        this.origin = origin;
    }

    @Override
    void drawImage(Graphics g) {
        if (MapConfig.isPathVisible()) {
            Graphics2D g2d = (Graphics2D) g;
            Path tempPath = calculatePath(getOrigin(), getDestination(), MapConfig.getPathWidth());
            g2d.drawLine((int) tempPath.getOrigin().getX(),
                    (int) tempPath.getOrigin().getY(),
                    (int) tempPath.getDestination().getX(),
                    (int) tempPath.getDestination().getY());
        }
    }

    @Override
    BufferedImage getImage() {
        return null;
    }

    @Override
    void inspect() {

    }
}
