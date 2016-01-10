/**
 * Created by Tymek on 13.10.15.
 */
class PassengerInfo {
    private int maxPassengerCount;
    private int actualPassengerCount;

    public PassengerInfo() {
        maxPassengerCount = MapConfig.randInt(MapConfig.getMinAirPassengers(), MapConfig.getMaxAirPassengers());
        actualPassengerCount = MapConfig.randInt(0, maxPassengerCount);
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
