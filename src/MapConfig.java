import javafx.geometry.Pos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Tymek on 30.12.2015.
 */


/*
@TODO
Paliwo i inne pierdoly w samolotach
Inspector:
-pokazywanie obiektu zaznaczonego
-jego trasy
-jego informacji
-checkboxy do tras i skrzyzowan
-Fix wylatujacych samolotow? Zmiana systemu podrozy?


*/
public final class MapConfig {

    private static BufferedImage passAirportImg;
    private static BufferedImage crossroadImg;
    private static BufferedImage passAircraftImg;
    private static BufferedImage miliAirportImg;
    private static BufferedImage miliAircraftImg;
    private static BufferedImage aircraftCarrierImg;

    private static Random rn = new Random();

    private static int pathWidth = 4;
    private static int maxAirPassengers = 20;
    private static int minAirPassengers = 5;
    private static int minRouteLength = 2;
    private static int maxRouteLength = 6;

    private static boolean crossroadVisible = false;
    private static boolean pathVisible = true;


    private static PassengerAirport[] passengerAirports = {
            new PassengerAirport(150, 100),
            new PassengerAirport(450, 150),
            new PassengerAirport(250, 380),
            new PassengerAirport(100, 450),
            new PassengerAirport(500, 400),
            new PassengerAirport(230, 660),
            new PassengerAirport(480, 590)
    };

    private static MilitaryAirport[] militaryAirports = {
            new MilitaryAirport(50, 200),
            new MilitaryAirport(700, 300),
            new MilitaryAirport(400, 700),
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
            crossroadImg = ImageIO.read(new File("resources/crossroad.png"));

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

        int randomNum = rn.nextInt((max - min) + 1) + min;

        return randomNum;
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

    public static boolean isPathVisible() {
        return pathVisible;
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
}
