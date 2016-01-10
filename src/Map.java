import java.awt.geom.Line2D;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Tymek on 28.12.2015.
 */

/**
 * Class of Map which creates lists of objects and loads parameters from Map Config
 */
public final class Map implements Serializable{

    private LinkedList<PhysicalObject> objectsToDraw;
    private LinkedList<Crossroad> crossroadsList;
    private LinkedList<Building> airportList;
    private LinkedList<Building> harborList;
    private LinkedList<Building> passBuildings;
    private LinkedList<Vehicle> vehiclesList;
    private LinkedList<Passenger> passengerLinkedList;

    /**
     * Constructor which creates lists of objects, randoms connections between Buildings.
     * Calculates crossroads.
     */
    public Map() {
        this.vehiclesList = new LinkedList<>();
        this.harborList = new LinkedList<>();
        this.airportList = new LinkedList<>();
        this.objectsToDraw = new LinkedList<>();
        this.passengerLinkedList = new LinkedList<>();

        this.loadHarbors();
        this.loadPassAirports();
        this.passBuildings = new LinkedList<>(this.airportList);
        this.loadMiliAirports();
        this.passBuildings.addAll(this.harborList);

        LinkedList<Path> airPathList = new LinkedList<>();
        LinkedList<Path> seaPathList = new LinkedList<>();
        this.createConnections(MapConfig.getHarbors(), this.harborList, seaPathList);
        this.createConnections(MapConfig.getPassengerAirports(), this.airportList, airPathList);
        this.createConnections(MapConfig.getMilitaryAirports(), this.airportList, airPathList);


        this.crossroadsList = new LinkedList<>();
        this.calculateCrossroads(airPathList);
        this.calculateCrossroads(seaPathList);

    }

    /**
     * Creates around 3 connections from every building in given Building array to Building from given list.
     * Checks in Path List if path doesn't exist.
     * @param a Building array
     * @param l Building list
     * @param pathlist paths list
     */
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
                    this.addObjectToDraw(one);
                    this.addObjectToDraw(two);
                }
            }
        }
    }

    /**
     * Calculates crossroads between paths. Because there are two paths between A and B: A->B and B->A it calculates
     * four crossroads per intersection. Feature not bug.
     * @param paths paths list
     */
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
                        this.crossroadsList.add(temp);
                        this.addObjectToDraw(temp);

                    }
                }
            }
        }
    }

    /**
     * Adds object to list of objects to draw
     * @param obj Physical Object
     */
    public void addObjectToDraw(PhysicalObject obj) {
        objectsToDraw.add(obj);
    }

    /**
     * Loads passenger airports from Map Config to Airport list and Objects to draw list.
     */
    private void loadPassAirports() {
        for (int i = 0; i < MapConfig.getPassengerAirports().length; i++) {
            this.addObjectToDraw(MapConfig.getPassengerAirports()[i]);
            this.addAirport(MapConfig.getPassengerAirports()[i]);
        }
    }
    /**
     * Loads military airports from Map Config to Airport list and Objects to draw list.
     */
    private void loadMiliAirports() {
        for (int i = 0; i < MapConfig.getMilitaryAirports().length; i++) {
            this.addObjectToDraw(MapConfig.getMilitaryAirports()[i]);
            this.addAirport(MapConfig.getMilitaryAirports()[i]);
        }
    }
    /**
     * Loads harbors from Map Config to Airport list and Objects to draw list.
     */
    private void loadHarbors() {
        for (int i = 0; i < MapConfig.getHarbors().length; i++) {
            this.addObjectToDraw(MapConfig.getHarbors()[i]);
            this.addHarbor(MapConfig.getHarbors()[i]);
        }
    }

    /**
     *
     * @return returns objects to draw list
     */
    public LinkedList<PhysicalObject> getObjectsToDraw() {
        return objectsToDraw;
    }

    /**
     * Adds single airport to airports list.
     * @param a Airport
     */
    private void addAirport(Airport a) {
        this.airportList.add(a);
    }

    /**
     * Adds single harbor to harbors list.
     * @param h Harbor
     */
    private void addHarbor(Harbor h) {
        this.harborList.add(h);
    }

    /**
     *
     * @return returns airports list.
     */
    public LinkedList<Building> getAirportList() {
        return this.airportList;
    }

    /**
     *
     * @return passenger buildings list.
     */
    public LinkedList<Building> getPassBuildings() {
        return this.passBuildings;
    }

    /**
     * Spawns passenger at random passenger location
     */
    public void spawnPassenger(){
        Runnable r = new Passenger();
        Thread t = new Thread(r);
        t.start();
    }

    /**
     *
     * @return returns list of vehicles
     */
    public LinkedList<Vehicle> getVehiclesList() {
        return this.vehiclesList;
    }

    /**
     *
     * @return returns list of passengers
     */
    public LinkedList<Passenger> getPassengerLinkedList() {
        return this.passengerLinkedList;
    }

}
