import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Tymek on 13.10.15.
 */

/**
 * Passenger class
 */
public class Passenger implements Runnable, Serializable{
    private String name;
    private String surname;
    private String peselId;
    private int age;
    private Vehicle actualVehicle;
    private Building homeBuilding;
    private Building actualBuilding;
    private Building destination;
    private boolean travelling;

    /**
     * Default constructor which generates Name, Surname, PESEL and age.
     */
    public Passenger() {
        this.name = new String(this.randomString());
        this.surname = new String(this.randomString());
        this.peselId = new String(this.randomPesel());
        this.age = 25;
      //  System.out.println("peep spawned " + this.toString() );
        WorldController.getMainMap().getPassengerLinkedList().add(this);
    }

    /**
     *
     * @return returns random string of length of 20 generated from alphabet
     */
    public String randomString(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

    /**
     *
     * @return returns random string of length of 12 generated from numbers 0-9
     */
    public String randomPesel(){
        char[] chars = "0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

    /**
     *
     * @return returns random destinations from whole connections net in which Actual Building is
     */
    public Building randomDestination(){
        Building dest = this.actualBuilding.getRandomConnected();
        return dest;
    }

    /**
     *  Run method containing infinite loop
     */
    @Override
    public void run() {
        this.homeBuilding = WorldController.getMainMap().getPassBuildings().get(MapConfig.randInt(0,
                WorldController.getMainMap().getPassBuildings().size() -1));
        this.actualBuilding = this.homeBuilding;
        this.actualBuilding.addToPeople(this);
        this.destination = this.randomDestination();
        this.travelling = false;


        while(true){
            if(!this.travelling){
                Iterator<Vehicle> itr =  this.actualBuilding.getVehicles().iterator();
                while(itr.hasNext()){
                    Vehicle veh = itr.next();
                    if(veh.getRoute().contains(this.destination)){
                        if(veh.getPassengerLinkedList().size() < veh.getMaxPassengerCount()){
                            this.actualVehicle = veh;
                            veh.addPassenger(this);
                            this.actualBuilding.deleteFromPeople(this);
                            this.travelling = true;
                            break;
                        }

                    }
                }
            }
            else{
                if(this.actualVehicle.isLanded()){
                    if(this.actualVehicle.getNextDestination().equals(this.destination)){
                        this.actualBuilding = this.destination;
                        this.actualVehicle.removePassenger(this);
                        this.actualBuilding.addToPeople(this);
                        this.travelling = false;
                    }
                }
            }
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
