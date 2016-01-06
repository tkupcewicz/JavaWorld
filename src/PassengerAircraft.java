/**
 * Created by Tymek on 13.10.15.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.UUID;

public class PassengerAircraft extends Aircraft {
    private PassengerInfo passengerInfo;

    public PassengerAircraft() {
        this.setUniqueId(UUID.randomUUID().toString());
        WorldController.getMainMap().addObjectToDraw(this);
        this.setCurrentBuilding(MapConfig.getPassengerAirports()[MapConfig.randInt(0,
                MapConfig.getPassengerAirports().length - 1)]);
        this.setPosition(this.getCurrentBuilding().getPosition().getX(), this.getCurrentBuilding().getPosition().getY());
        this.passengerInfo = new PassengerInfo();

        this.setSpeed(3);
    }

    @Override
    void drawImage(Graphics g) {
        g.drawImage(
                MapConfig.getPassAircraftImg(),
                (int) this.getPosition().getX() - MapConfig.getPassAircraftImg().getWidth()/2,
                (int) this.getPosition().getY() - MapConfig.getPassAircraftImg().getHeight()/2,
                WorldController.getWorldController());
    }

    @Override
    BufferedImage getImage() {
        return MapConfig.getPassAircraftImg();
    }

    void getPassengers() {

    }

    void leavePassengers() {

    }

    public void refuel() {

    }

    void leaveAirport() {

    }

    @Override
    void moveTo() {

    }

    @Override
    public void arrive(){
        this.refuel();
    }

}
