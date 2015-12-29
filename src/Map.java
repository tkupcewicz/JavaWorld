/**
 * Created by Tymek on 28.12.2015.
 */
public class Map {
    private MilitaryAirport[] miliAirportArray = new MilitaryAirport[3];
    private PassengerAirport[] passAirportArray = new PassengerAirport[7];



    public Map(){
        for(int i = 0; i < miliAirportArray.length; i++){
            miliAirportArray[i] = new MilitaryAirport();
        }
        miliAirportArray[0].setPosition(100, 200);
        miliAirportArray[1].setPosition(700, 300);
        miliAirportArray[2].setPosition(400, 700);

        for(int i = 0; i < passAirportArray.length; i++){
            passAirportArray[i] = new PassengerAirport();
        }
        passAirportArray[0].setPosition(150, 100);
        passAirportArray[1].setPosition(300, 300);
        passAirportArray[2].setPosition(450, 150);
        passAirportArray[3].setPosition(650, 550);
        passAirportArray[4].setPosition(700, 700);
        passAirportArray[5].setPosition(230, 660);
        passAirportArray[6].setPosition(500, 400);
    }

    public MilitaryAirport[] getMilitaryAirports(){
        return miliAirportArray;
    }

    public PassengerAirport[] getPassengerAirports() {
        return passAirportArray;
    }
}
