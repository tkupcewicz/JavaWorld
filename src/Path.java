/**
 * Created by Tymek on 15.10.15.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.*;

public class Path extends PhysicalObject{
    private Position destination;
    private Position origin;

    public Path(){

    }

    public Path(Position a, Position b){
        this.destination = a;
        this.origin = b;
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

    public static Path calculatePath(Position A, Position B, int pathWidth){
        int xA = A.getX();
        int yA = A.getY();
        int xB = B.getX();
        int yB = B.getY();
        double d = Math.sqrt(Math.pow((xA - xB), 2) + Math.pow((yA - yB),2));

        double xAp = xA + pathWidth * (yB - yA) / d;
        double yAp = yA + pathWidth * (xA - xB) / d;
        double xBp = xB + pathWidth * (yB - yA) / d;
        double yBp = yB + pathWidth * (xA - xB) / d;
        Position aPrim = new Position((int)Math.round(xAp),(int)Math.round(yAp));
        Position bPrim = new Position((int)Math.round(xBp),(int)Math.round(yBp));
        Path wynik = new Path(aPrim,bPrim);
        return wynik;
    }

    @Override
    void drawImage(Graphics g) {
        if(MapConfig.isPathVisible()){
            Graphics2D g2d = (Graphics2D) g;
            Path tempPath = Path.calculatePath(this.getOrigin(), this.getDestination(), MapConfig.getPathWidth());
            g2d.drawLine(tempPath.getOrigin().getX(),
                    tempPath.getOrigin().getY(),
                    tempPath.getDestination().getX(),
                    tempPath.getDestination().getY());
        }
    }
}
