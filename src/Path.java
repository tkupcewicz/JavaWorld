/**
 * Created by Tymek on 15.10.15.
 */

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Path class
 */
public class Path extends PhysicalObject {
    private Position destination;
    private Position origin;

    /**
     * Constructor which creates path of given positions A and B
     * @param a Position
     * @param b Position
     */
    public Path(Position a, Position b) {
        this.destination = a;
        this.origin = b;
    }

    /**
     * Calculates one path between two Positions moved to left if pathWidth negative or right if it's positiove
     * @param A Position
     * @param B Position
     * @param pathWidth integer of which Path will be moved to right or left
     * @return returns calculated Path
     */
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

    /**
     *
     * @return returns first position of path
     */
    public Position getDestination() {
        return this.destination;
    }

    /**
     *
     * @return returns second position of path
     */
    public Position getOrigin() {
        return this.origin;
    }

    /**
     * Draws line between two points of path
     * @param g image
     */
    @Override
    void drawImage(Graphics g) {
        if (MapConfig.isPathVisible()) {
            Graphics2D g2d = (Graphics2D) g;
            //noinspection UnqualifiedMethodAccess,UnqualifiedMethodAccess
            Path tempPath = calculatePath(this.getOrigin(), this.getDestination(), MapConfig.getPathWidth());
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

    /**
     * Method created by lack of time to create interface
     */
    @Override
    void inspect() {

    }
}
