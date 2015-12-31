/**
 * Created by Tymek on 15.10.15.
 */
import java.math.*;

public class Path {
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

    public static Path calcuatePath(Position A, Position B){
        int xA = A.getX();
        int yA = A.getY();
        int xB = B.getX();
        int yB = B.getY();
        double d = Math.sqrt(Math.pow((xA - xB), 2) + Math.pow((yA - yB),2));

        double xAp = xA + MapConfig.getPathWidth() * (yB - yA) / d;
        double yAp = yA + MapConfig.getPathWidth() * (xA - xB) / d;
        double xBp = xB + MapConfig.getPathWidth() * (yB - yA) / d;
        double yBp = yB + MapConfig.getPathWidth() * (xA - xB) / d;
        Position aPrim = new Position((int)Math.round(xAp),(int)Math.round(yAp));
        Position bPrim = new Position((int)Math.round(xBp),(int)Math.round(yBp));
        Path wynik = new Path(aPrim,bPrim);
        return wynik;
    }
}
