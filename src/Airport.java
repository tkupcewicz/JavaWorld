/**
 * Created by Tymek on 15.10.15.
 */
public abstract class Airport extends Building {
    private int aircraftCapacity;

    abstract void receiveAircraft();
    abstract void sendAircraft();
}
