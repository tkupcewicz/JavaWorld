/**
 * Created by Tymek on 13.10.15.
 */
public abstract class Aircraft extends Vehicle{
    private float maxFuel;
    private float actualFuel;
    private int stuffCount;
    private Position currentAirport;

    public float getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(float maxFuel) {
        this.maxFuel = maxFuel;
    }

    public float getActualFuel() {
        return actualFuel;
    }

    public void setActualFuel(float actualFuel) {
        this.actualFuel = actualFuel;
    }

    public int getStuffCount() {
        return stuffCount;
    }

    public void setStuffCount(int stuffCount) {
        this.stuffCount = stuffCount;
    }

    public Position getCurrentAirport() {
        return currentAirport;
    }

    public void setCurrentAirport(Position currentAirport) {
        this.currentAirport = currentAirport;
    }
}
