/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class PassengerAircraft extends Aircraft {
    private PassengerInfo passengerInfo;

    public PassengerAircraft() {

        this.setCurrentAirport(MapConfig.getPassengerAirports()[MapConfig.randInt(0,MapConfig.getPassengerAirports().length - 1)]);
        this.passengerInfo = new PassengerInfo();

        this.randomizeRoute();

        System.out.println("Plane spawned at " + this.getCurrentAirport().getPosition().toString());
    }

    @Override
    void drawImage(Graphics g) {

    }

    void getPassengers() {

    }

    void leavePassengers() {

    }

    void refuel() {

    }

    void leaveAirport() {

    }

    @Override
    void moveTo() {

    }

    @Override
    Building randomizeRoute() {
      //   this.getCurrentAirport().getConnections(MapConfig.randInt(0, this.getCurrentAirport().getConnections().size()-1));
        return null;
    }
}
