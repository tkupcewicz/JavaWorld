/**
 * Created by Tymek on 13.10.15.
 */
public class Passenger {
    private String name;
    private String surname;
    private String peselId;
    private int age;
    private Position homePosition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPeselId() {
        return peselId;
    }

    public void setPeselId(String peselId) {
        this.peselId = peselId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Position getHomePosition() {
        return homePosition;
    }

    public void setHomePosition(Position homePosition) {
        this.homePosition = homePosition;
    }


}
