import java.io.Serializable;

/**
 * Created by Tymek on 22.10.15.
 */

/**
 * Enum of Weapons used by Aircraft Carriers and Military Planes
 */
public enum Weapon implements Serializable{
    Weapon1, Weapon2, Weapon3, Weapon4, Weapon5;

    public static Weapon getRandom() {
        return Weapon.values()[(int) (Math.random() * Weapon.values().length)];
    }


}
