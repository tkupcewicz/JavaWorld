/**
 * Created by Tymek on 22.10.15.
 */
public enum Weapon {
    Weapon1, Weapon2, Weapon3, Weapon4, Weapon5;

    public static Weapon getRandom() {
        return Weapon.values()[(int) (Math.random() * Weapon.values().length)];
    }


}
