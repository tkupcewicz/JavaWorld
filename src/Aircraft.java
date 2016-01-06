/**
 * Created by Tymek on 13.10.15.
 */
public abstract class Aircraft extends Vehicle{
    private float maxFuel;
    private float actualFuel;
    private int stuffCount;

    public void refuel(){
        this.actualFuel = this.maxFuel;
    }

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

}
