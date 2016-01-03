import java.util.LinkedList;

/**
 * Created by Tymek on 13.10.15.
 */

public abstract class Vehicle extends PhysicalObject implements Runnable {
    private int uniqueId;
    private Building nextDestination;
    private LinkedList<Building> route;
    abstract void moveTo();
    abstract LinkedList<Building> randomizeRoute();

    public void run() {
        this.nextDestination = route.get(0);
//        while(true){
//           position = position + speed;
//           fuel = fuel - fuelpersec;
//           if(route[0] == crossroad){
//              CHUJEMUJE SEMAFORY ;]]
//           }
//           else{
//              if(position == route[0].position){
//                 this.land();
//              }
//           }
//
//        }
    }
}
