import java.awt.geom.Line2D;
import java.util.LinkedList;

/**
 * Created by Tymek on 28.12.2015.
 */
@SuppressWarnings("ForLoopReplaceableByForEach")
public final class Map {

    private static LinkedList<PhysicalObject> objectsToDraw;
    private final LinkedList<Crossroad> crossroadsList;
    private final LinkedList<Building> airportList;
    private final LinkedList<Building> harborList;

    public Map() {

        harborList = new LinkedList<>();
        airportList = new LinkedList<>();
        objectsToDraw = new LinkedList<>();

        loadHarbors();
        loadPassAirports();
        loadMiliAirports();


        LinkedList<Path> airPathList = new LinkedList<>();
        LinkedList<Path> seaPathList = new LinkedList<>();
        createConnections(MapConfig.getHarbors(), harborList, seaPathList);
        createConnections(MapConfig.getPassengerAirports(), airportList, airPathList);
        createConnections(MapConfig.getMilitaryAirports(), airportList, airPathList);


        crossroadsList = new LinkedList<>();
        calculateCrossroads(airPathList);
        calculateCrossroads(seaPathList);

        //noinspection ForLoopReplaceableByForEach
        for (Crossroad aCrossroadsList : crossroadsList) {
            System.out.println(aCrossroadsList.getPosition());
        }


    }

    private void createConnections(Building a[], LinkedList<Building> l, LinkedList<Path> pathlist) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < MapConfig.randInt(3, 3); j++) {
                int x = MapConfig.randInt(i + 1, l.size() - 1);
                if (!a[i].getConnections().contains(l.get(x))) {
                    a[i].addConnection(l.get(x));
                    l.get(x).addConnection(a[i]);
                    Path one = new Path(a[i].getPosition(), l.get(x).getPosition());
                    Path two = new Path(l.get(x).getPosition(), a[i].getPosition());
                    pathlist.add(one);
                    pathlist.add(two);
                    addObjectToDraw(one);
                    addObjectToDraw(two);
                }
            }
        }
    }

    private void calculateCrossroads(LinkedList<Path> paths) {
        for (int i = 0; i < paths.size() - 1; i++) {
            for (int j = i + 1; j < paths.size(); j++) {
                float x1, y1, x2, y2, x3, y3, x4, y4;
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
                        float d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
                        float xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
                        float yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

                        Crossroad temp = new Crossroad(xi, yi);
                        crossroadsList.add(temp);
                        addObjectToDraw(temp);

                    }
                }
            }
        }
    }


    public PassengerAirport[] getPassengerAirports() {
        return MapConfig.getPassengerAirports();
    }

    public void addObjectToDraw(PhysicalObject obj) {
        objectsToDraw.add(obj);
    }

    private void loadPassAirports() {
        for (int i = 0; i < MapConfig.getPassengerAirports().length; i++) {
            addObjectToDraw(MapConfig.getPassengerAirports()[i]);
            addAirport(MapConfig.getPassengerAirports()[i]);
        }
    }

    private void loadMiliAirports() {
        for (int i = 0; i < MapConfig.getMilitaryAirports().length; i++) {
            addObjectToDraw(MapConfig.getMilitaryAirports()[i]);
            addAirport(MapConfig.getMilitaryAirports()[i]);
        }
    }

    private void loadHarbors() {
        for (int i = 0; i < MapConfig.getHarbors().length; i++) {
            addObjectToDraw(MapConfig.getHarbors()[i]);
            addHarbor(MapConfig.getHarbors()[i]);
        }
    }

    public LinkedList<PhysicalObject> getObjectsToDraw() {
        return objectsToDraw;
    }

    private void addAirport(Airport a) {
        airportList.add(a);
    }

    private void addHarbor(Harbor h) {
        harborList.add(h);
    }

    public LinkedList<Building> getAirportList() {
        return airportList;
    }

}
