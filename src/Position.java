import java.io.Serializable;

/**
 * Created by Tymek on 13.10.15.
 */

/**
 * Position class
 */
public class Position implements Serializable{
    private float x;
    private float y;

    /**
     * Constructor which creates position of given coordinates X and Y
     * @param x float
     * @param y float
     */
    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return returns X of position
     */
    public float getX() {
        return this.x;
    }

    /**
     *
     * @return returns Y of position
     */
    public float getY() {
        return this.y;
    }

    /**
     *
     * @return returns string of position
     */
    @Override
    public String toString() {
        return "X: " + this.x + " Y: " + this.y + " ";
    }
}
