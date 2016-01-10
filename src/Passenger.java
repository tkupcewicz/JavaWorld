import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Tymek on 13.10.15.
 */
public class Passenger implements Runnable{
    private String name;
    private String surname;
    private String peselId;
    private int age;
    private Vehicle actualVehicle;
    private Building homeBuilding;
    private Building actualBuilding;
    private Building destination;
    private boolean travelling;

    public Passenger() {
        this.name = new String(this.randomString());
        this.surname = new String(this.randomString());
        this.peselId = new String(this.randomPesel());
        this.age = 25;
        System.out.println("peep spawned " + this.toString() );
    }

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

    public Building randomDestination(){
        Building dest = this.actualBuilding.getRandomConnected();
        return dest;
    }

    @Override
    public void run() {
        this.homeBuilding = WorldController.getMainMap().getPassBuildings().get(MapConfig.randInt(0,
                WorldController.getMainMap().getPassBuildings().size() -1));
        System.out.println("Home building is" + homeBuilding.toString() + " at " + homeBuilding.getPosition().toString());
        this.actualBuilding = this.homeBuilding;
        this.actualBuilding.addToPeople(this);
        this.destination = this.randomDestination();
        System.out.println("Destination building is" + destination.toString() + " at " + destination.getPosition().toString());
        this.travelling = false;


        while(true){
            if(!this.travelling){
//                System.out.println("Looking for transport");
                Iterator<Vehicle> itr =  this.actualBuilding.getVehicles().iterator();
                while(itr.hasNext()){
                    Vehicle veh = itr.next();
                    if(veh.getRoute().contains(this.destination)){
//                        System.out.println("Found transport in " + veh.toString());
                        this.actualVehicle = veh;
                        veh.addPassenger(this);
                        this.actualBuilding.deleteFromPeople(this);
                        this.travelling = true;
                        break;
                    }
                }
            }
            else{
//                System.out.println("Travelling");
                if(this.actualVehicle.isLanded()){
//                    System.out.println("Vehicle landed");
                    if(this.actualVehicle.getNextDestination().equals(this.destination)){
//                        System.out.println("Vehicle landed at right place");
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
