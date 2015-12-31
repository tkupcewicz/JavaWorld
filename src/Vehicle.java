import java.util.LinkedList;

/**
 * Created by Tymek on 13.10.15.
 */

public abstract class Vehicle extends PhysicalObject {
    private int uniqueId;
    private LinkedList<Position> route;
    abstract void moveTo();
    abstract LinkedList<Position> randomizeRoute();
}
