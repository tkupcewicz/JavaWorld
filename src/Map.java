import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Tymek on 28.12.2015.
 */
public class Map {

    public Map(){
        Crossroad[] tmpCross = MapConfig.getCrossroads();
        for(int i = 0; i < tmpCross.length; i++){
            tmpCross[i].setConnections(new ArrayList<Building>(Arrays.asList(MapConfig.getCrossroadsConnections()[i])));
        }
        PassengerAirport[] tmpPass = MapConfig.getPassengerAirports();
        for(int i = 0; i < tmpPass.length; i++){
            tmpPass[i].setConnections(new ArrayList<Building>(Arrays.asList(MapConfig.getPassConnections()[i])));
        }
        MilitaryAirport[] tmpMili = MapConfig.getMilitaryAirports();
        for(int i = 0; i < tmpMili.length; i++){
            tmpMili[i].setConnections(new ArrayList<Building>(Arrays.asList(MapConfig.getMiliConnections()[i])));
        }
    }

    public MilitaryAirport[] getMilitaryAirports(){
        return MapConfig.getMilitaryAirports();
    }

    public PassengerAirport[] getPassengerAirports() {
        return MapConfig.getPassengerAirports();
    }

    public Crossroad[] getCrossroadsArray() {
        return MapConfig.getCrossroads();
    }
}
