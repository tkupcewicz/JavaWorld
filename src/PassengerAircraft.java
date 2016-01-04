/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class PassengerAircraft extends Aircraft {
    private PassengerInfo passengerInfo;

    public PassengerAircraft() {
        WorldController.getMainMap().addObjectToDraw(this);
        this.setCurrentBuilding(MapConfig.getPassengerAirports()[MapConfig.randInt(0,
                MapConfig.getPassengerAirports().length - 1)]);
        this.setPosition(this.getCurrentBuilding().getPosition().getX(), this.getCurrentBuilding().getPosition().getY());
        this.passengerInfo = new PassengerInfo();
        this.randomizeRoute();
        this.setSpeed(2);
    }

    @Override
    void drawImage(Graphics g) {
        g.drawImage(
                MapConfig.getPassAircraftImg(),
                this.getPosition().getX() - MapConfig.getPassAircraftImg().getWidth()/2,
                this.getPosition().getY() - MapConfig.getPassAircraftImg().getHeight()/2,
                WorldController.getWorldController());
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
    void randomizeRoute() {
         this.setNextDestination((Building) this.getCurrentBuilding().getConnections().get(MapConfig.randInt(0,
                this.getCurrentBuilding().getConnections().size() - 1)));
    }
}
