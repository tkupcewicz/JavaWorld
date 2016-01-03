import javafx.geometry.Pos;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Tymek on 28.12.2015.
 */
public class Map {

    private LinkedList<Path> pathsList;
    private LinkedList<Crossroad> crossroadsList;
    private LinkedList<PhysicalObject> objectsToDraw;

    public Map(){

        objectsToDraw = new LinkedList<>();
        loadPassAirports();

        pathsList = new LinkedList<>();
        createConnections(MapConfig.getPassengerAirports());

        crossroadsList = new LinkedList<>();
        calculateCrossroads(pathsList);
    }

    public void createConnections(Building a[]){
        for (int i = 0; i < a.length-1; i++) {
            for (int j = 0; j < MapConfig.randInt(2,3); j++) {
                int x = MapConfig.randInt(i+1, a.length-1);
                if(!a[i].getConnections().contains(a[x])){
                    a[i].addConnection(a[x]);
                    a[x].addConnection(a[i]);
                    Path one = new Path(a[i].getPosition(), a[x].getPosition());
                    Path two = new Path(a[x].getPosition(), a[i].getPosition());
                    pathsList.add(one);
                    pathsList.add(two);
                    this.addObjectToDraw(one);
                    this.addObjectToDraw(two);

                }
            }
        }
    }

    public void calculateCrossroads(LinkedList<Path> paths){
        for (int i = 0; i < paths.size()-1; i++) {
            for (int j = i+1; j < paths.size(); j++) {
                int x1, y1, x2, y2, x3, y3, x4, y4;
                x1 = paths.get(i).getOrigin().getX();
                y1 = paths.get(i).getOrigin().getY();
                x2 = paths.get(i).getDestination().getX();
                y2 = paths.get(i).getDestination().getY();
                x3 = paths.get(j).getOrigin().getX();
                y3 = paths.get(j).getOrigin().getY();
                x4 = paths.get(j).getDestination().getX();
                y4 = paths.get(j).getDestination().getY();

                if (!(paths.get(i).getDestination().equals(paths.get(j).getDestination()) ||
                        paths.get(i).getDestination().equals(paths.get(j).getOrigin()) ||
                        paths.get(i).getOrigin().equals(paths.get(j).getDestination()) ||
                        paths.get(i).getOrigin().equals(paths.get(j).getOrigin()))) {
                    if (Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4)) {
                        int d = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);
                        int xi = ((x3-x4)*(x1*y2-y1*x2)-(x1-x2)*(x3*y4-y3*x4))/d;
                        int yi = ((y3-y4)*(x1*y2-y1*x2)-(y1-y2)*(x3*y4-y3*x4))/d;
                        Crossroad temp = new Crossroad(xi, yi);
                        crossroadsList.add(temp);
                        this.addObjectToDraw(temp);
                    }
                }
            }
        }
    }



    public PassengerAirport[] getPassengerAirports() {
        return MapConfig.getPassengerAirports();
    }

    public void addObjectToDraw(PhysicalObject obj) {
        this.objectsToDraw.add(obj);
    }

    public void loadPassAirports(){
        for (int i = 0; i < MapConfig.getPassengerAirports().length; i++) {
            this.addObjectToDraw(MapConfig.getPassengerAirports()[i]);
        }
    }

    public LinkedList<PhysicalObject> getObjectsToDraw() {
        return objectsToDraw;
    }
}
