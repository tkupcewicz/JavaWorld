import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by Tymek on 30.12.2015.
 */

final class MapConfig implements Serializable{

    private static transient BufferedImage passAirportImg;
    private static transient BufferedImage crossroadImg;
    private static transient BufferedImage passAircraftImg;
    private static transient BufferedImage miliAirportImg;
    private static transient BufferedImage miliAircraftImg;
    private static transient BufferedImage aircraftCarrierImg;
    private static transient BufferedImage harborImg;
    private static transient BufferedImage passengerShipImg;
    private static transient BufferedImage backgroundImg;

    private static Random rn = new Random();

    private static float vehiclesSpeed = 1;
    private static final int pathWidth = 4;
    private static final int maxAirPassengers = 20;
    private static final int minAirPassengers = 5;
    private static final int minRouteLength = 4;
    private static final int maxRouteLength = 10;
    private static final int minAirportCapacity = 3;
    private static final int maxAirportCapacity = 3;
    private static final int minVehicleSpeed = 2;
    private static final int maxVehicleSpeed = 4;
    private static final int minPassengerCount = 5;
    private static final int maxPassengerCount = 15;

    private static boolean crossroadVisible;
    private static boolean pathVisible = true;
    private static boolean backgroundVisible = true;

    private static String serFileName = "save.ser";


    private static final PassengerAirport[] passengerAirports = {
            new PassengerAirport(150, 100),
            new PassengerAirport(450, 150),
            new PassengerAirport(250, 380),
            new PassengerAirport(100, 450),
            new PassengerAirport(500, 400),
            new PassengerAirport(230, 660),
            new PassengerAirport(480, 590)
    };

    private static final MilitaryAirport[] militaryAirports = {
            new MilitaryAirport(50, 200),
            new MilitaryAirport(550, 300),
            new MilitaryAirport(400, 700),
    };

    private static final Harbor[] harbors = {
            new Harbor(800, 50),
            new Harbor(680, 75),
            new Harbor(960, 230),
            new Harbor(630, 350),
            new Harbor(1000, 370),
            new Harbor(750, 650),
            new Harbor(900, 680)
    };

    static {
        try {
            passAirportImg = ImageIO.read(new File("resources/passAirport.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    static {
        try {
            harborImg = ImageIO.read(new File("resources/port.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    static {
        try {
            backgroundImg = ImageIO.read(new File("resources/map.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    static {
        try {
            crossroadImg = ImageIO.read(new File("resources/circle.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    static {
        try {
            passAircraftImg = ImageIO.read(new File("resources/passPlane.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    static {
        try {
            miliAircraftImg = ImageIO.read(new File("resources/miliPlane.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    static {
        try {
            miliAirportImg = ImageIO.read(new File("resources/miliAirport.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    static {
        try {
            aircraftCarrierImg = ImageIO.read(new File("resources/carrier.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    static {
        try {
            passengerShipImg = ImageIO.read(new File("resources/passShip.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public static PassengerAirport[] getPassengerAirports() {
        return passengerAirports;
    }

    public static MilitaryAirport[] getMilitaryAirports() {
        return militaryAirports;
    }

    public static int getPathWidth() {
        return pathWidth;
    }

    public static BufferedImage getPassAirportImg() {
        return passAirportImg;
    }

    public static Random getRn() {
        return rn;
    }

    public static void setRn(Random rn) {
        MapConfig.rn = rn;
    }

    public static int getMaxAirPassengers() {
        return maxAirPassengers;
    }

    public static int getMinAirPassengers() {
        return minAirPassengers;
    }

    public static int randInt(int min, int max) {

        return rn.nextInt(max - min + 1) + min;
    }

    public static int getMinRouteLength() {
        return minRouteLength;
    }

    public static int getMaxRouteLength() {
        return maxRouteLength;
    }

    public static BufferedImage getCrossroadImg() {
        return crossroadImg;
    }

    public static boolean isCrossroadVisible() {
        return crossroadVisible;
    }

    public static void setCrossroadVisible(boolean crossroadVisible) {
        MapConfig.crossroadVisible = crossroadVisible;
    }

    public static boolean isPathVisible() {
        return pathVisible;
    }

    public static void setPathVisible(boolean pathVisible) {
        MapConfig.pathVisible = pathVisible;
    }

    public static BufferedImage getPassAircraftImg() {
        return passAircraftImg;
    }

    public static BufferedImage getMiliAirportImg() {
        return miliAirportImg;
    }

    public static BufferedImage getMiliAircraftImg() {
        return miliAircraftImg;
    }

    public static BufferedImage getAircraftCarrierImg() {
        return aircraftCarrierImg;
    }

    public static int getMinAirportCapacity() {
        return minAirportCapacity;
    }

    public static int getMaxAirportCapacity() {
        return maxAirportCapacity;
    }

    public static float getVehiclesSpeed() {
        return vehiclesSpeed;
    }

    public static void setVehiclesSpeed(float vehiclesSpeed) {
        MapConfig.vehiclesSpeed = vehiclesSpeed;
    }

    public static BufferedImage getHarborImg() {
        return harborImg;
    }

    public static Harbor[] getHarbors() {
        return harbors;
    }

    public static int getMinVehicleSpeed() {
        return minVehicleSpeed;
    }

    public static int getMaxVehicleSpeed() {
        return maxVehicleSpeed;
    }

    public static BufferedImage getPassengerShipImg() {
        return passengerShipImg;
    }

    public static int getMinPassengerCount() {
        return MapConfig.minPassengerCount;
    }

    public static int getMaxPassengerCount() {
        return MapConfig.maxPassengerCount;
    }

    public static String getSerFileName() {
        return MapConfig.serFileName;
    }

    public static BufferedImage getBackgroundImg() {
        return MapConfig.backgroundImg;
    }

    public static boolean isBackgroundVisible() {
        return MapConfig.backgroundVisible;
    }

    public static void setBackgroundVisible(boolean backgroundVisible) {
        MapConfig.backgroundVisible = backgroundVisible;
    }
}
