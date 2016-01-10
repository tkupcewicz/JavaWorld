import java.io.Serializable;

/**
 * Created by Tymek on 13.10.15.
 */

/**
 * Passenger Info class, basically a container for Passenger numbers
 */
class PassengerInfo implements Serializable{
    private int maxPassengerCount;
    private int actualPassengerCount;

    /**
     * Constructor which sets Max Count to number from config, and randoms Actual Count from 0 to Max
     */
    public PassengerInfo() {
        this.maxPassengerCount = MapConfig.randInt(MapConfig.getMinAirPassengers(), MapConfig.getMaxAirPassengers());
        this.actualPassengerCount = MapConfig.randInt(0, this.maxPassengerCount);
    }
}
