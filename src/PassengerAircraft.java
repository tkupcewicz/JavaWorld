/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class PassengerAircraft extends Aircraft {
    private PassengerInfo passengerInfo;

    public PassengerAircraft() {

        this.setCurrentAirport(MapConfig.getPassengerAirports()[MapConfig.randInt(0, MapConfig.getPassengerAirports().length)]);
        this.passengerInfo = new PassengerInfo();
        this.randomizeRoute();
        // this.setMaxFuel();
        System.out.println("Plane spawned at " + this.getCurrentAirport().getPosition().toString());
    }

    @Override
    void drawImage(Graphics g) {

    }

    @Override
    BufferedImage getImage() {
        return null;
    }

    void getPassengers() {

    }

    void leavePassengers() {

    }

    /*
    TODO:
    Wywalic crossroadsy
    Zostawic lotniska
    Lotnisko ma (1,n) polaczen
    Lista polaczen, babelkowo generowane skrzyzowania
    Olac semafory az do konca projektu
    Na jutro:
    Generowanie trasy - lot do losowego lotniska
    Poruszanie sie i generowanie nowej po dotarciu
    Dummy funckje odbioru pasazerow, tankowania etc
    Przerobienie syfu w kodzie
    

    */


    void refuel() {

    }

    void leaveAirport() {

    }

    @Override
    void moveTo() {

    }

    @Override
    LinkedList<Building> randomizeRoute() {
        return null;
    }
}
