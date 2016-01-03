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
New samolot na jakims lotnisku
Randomizowanie trasy
Generowanie wartosci, pojemnosc, bak etc
Poruszanie sie z ustalona predkoscia
Po doleceniu - nowa trasa


*/
public final class MapConfig {

    private static BufferedImage passAirportImg;
    private static Random rn = new Random();

    static {
        try {
                passAirportImg = ImageIO.read(new File("resources/passAirport.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static int pathWidth = 4;
    private static int maxAirPassengers = 20;
    private static int minAirPassengers = 5;
    private static int minRouteLength = 2;
    private static int maxRouteLength = 6;


    private static PassengerAirport[] passengerAirports = {
            new PassengerAirport(150, 100),
            new PassengerAirport(450, 150),
            new PassengerAirport(300, 300),
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

    private static Crossroad[] crossroads = {
            new Crossroad(265, 185),
            new Crossroad(470, 280),
            new Crossroad(290, 420),
            new Crossroad(400, 530)
    };

    private static Building[][] passConnections = {
            {crossroads[0]},
            {crossroads[0], crossroads[1]},
            {crossroads[0], crossroads[1], crossroads[2]},
            {crossroads[2]},
            {crossroads[1], crossroads[2], crossroads[3]},
            {crossroads[2], crossroads[3]},
            {crossroads[3]}
    };

    private static Building[][] crossroadsConnections = {
            {passengerAirports[0], passengerAirports[1], passengerAirports[2],crossroads[1], militaryAirports[0]},
            {passengerAirports[1], passengerAirports[2], crossroads[0], crossroads[2],passengerAirports[4],militaryAirports[1]},
            {passengerAirports[2], passengerAirports[3], passengerAirports[4], passengerAirports[5], crossroads[1], crossroads[3]},
            {passengerAirports[5], passengerAirports[6], passengerAirports[4], crossroads[2], militaryAirports[2]}
    };

    private static Building[][] miliConnections = {
            {crossroads[0]},
            {crossroads[1]},
            {crossroads[3]}
    };



    public static PassengerAirport[] getPassengerAirports() {
        return passengerAirports;
    }

    public static MilitaryAirport[] getMilitaryAirports() {
        return militaryAirports;
    }

    public static Crossroad[] getCrossroads() {
        return crossroads;
    }

    public static int getPathWidth() {
        return pathWidth;
    }

    public static BufferedImage getPassAirportImg() {
        return passAirportImg;
    }

    public static Building[][] getPassConnections() {
        return passConnections;
    }

    public static Building[][] getCrossroadsConnections() {
        return crossroadsConnections;
    }

    public static Building[][] getMiliConnections() {
        return miliConnections;
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
}
