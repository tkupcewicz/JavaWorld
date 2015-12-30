import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Tymek on 28.12.2015.
 */
public class Map {
    private MilitaryAirport[] miliAirportArray;
    private PassengerAirport[] passAirportArray;
    private Crossroad[] crossroadsArray;



    public Map(){
        crossroadsArray = new Crossroad[MapConfig.getCrossroadsPositions().length];

        for(int i = 0; i < crossroadsArray.length; i++){
            crossroadsArray[i] = new Crossroad();
            crossroadsArray[i].setPosition(MapConfig.getCrossroadsPositions()[i].getX(), MapConfig.getCrossroadsPositions()[i].getY());
        }

        miliAirportArray = new MilitaryAirport[MapConfig.getMiliAirportPositions().length];

        for(int i = 0; i < miliAirportArray.length; i++){
            miliAirportArray[i] = new MilitaryAirport();
            miliAirportArray[i].setPosition(MapConfig.getMiliAirportPositions()[i].getX(), MapConfig.getMiliAirportPositions()[i].getY());
        }

        passAirportArray = new PassengerAirport[MapConfig.getPassAirportPositions().length];

        for(int i = 0; i < passAirportArray.length; i++){
            passAirportArray[i] = new PassengerAirport();
            passAirportArray[i].setPosition(MapConfig.getPassAirportPositions()[i].getX(), MapConfig.getPassAirportPositions()[i].getY());
            passAirportArray[i].setConnections(new ArrayList<Position>(Arrays.asList(MapConfig.getPassConnections()[i])));

        }

    }


    public MilitaryAirport[] getMilitaryAirports(){
        return miliAirportArray;
    }

    public PassengerAirport[] getPassengerAirports() {
        return passAirportArray;
    }

    public Crossroad[] getCrossroadsArray() {
        return crossroadsArray;
    }
}
