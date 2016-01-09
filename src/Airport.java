import java.awt.*;

/**
 * Created by Tymek on 15.10.15.
 */
public abstract class Airport extends Building {

    private int aircraftCapacity;
    abstract void receiveAircraft();
    abstract void sendAircraft();
    public Airport(){
        this.aircraftCapacity = MapConfig.randInt(MapConfig.getMinAirportCapacity(), MapConfig.getMaxAirportCapacity());
    }
}
