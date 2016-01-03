/**
 * Created by Tymek on 13.10.15.
 */
public class PassengerInfo {
    private int maxPassengerCount;
    private int actualPassengerCount;

    public PassengerInfo() {
        this.maxPassengerCount = MapConfig.randInt(MapConfig.getMinAirPassengers(), MapConfig.getMaxAirPassengers());
        this.actualPassengerCount = MapConfig.randInt(0, this.maxPassengerCount);
    }

    public int getMaxPassengerCount() {
        return maxPassengerCount;
    }

    public void setMaxPassengerCount(int maxPassengerCount) {
        this.maxPassengerCount = maxPassengerCount;
    }

    public int getActualPassengerCount() {
        return actualPassengerCount;
    }

    public void setActualPassengerCount(int actualPassengerCount) {
        this.actualPassengerCount = actualPassengerCount;
    }
}
