import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Tymek on 30.12.2015.
 */
public final class MapConfig {

    private static BufferedImage passAirportImg;

    static {
        try {
//            System.out.println(new File("resources/passAirport.png").getCanonicalPath());
                passAirportImg = ImageIO.read(new File("resources/passAirport.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static int pathWidth = 8;

    private static Position[] passAirportPositions = {
            new Position(150, 100),
            new Position(450, 150),
            new Position(300, 300),
            new Position(100, 450),
            new Position(500, 400),
            new Position(230, 660),
            new Position(650, 550)

    };

    private static Position[] miliAirportPositions = {
            new Position(100, 200),
            new Position(700, 300),
            new Position(400, 700),
    };

    private static Position[] crossroadsPositions = {
            new Position(265, 185),
            new Position(470, 280),
            new Position(290, 420),
            new Position(400, 530)
    };

    private static Position[][] passConnections = {
            {crossroadsPositions[0], passAirportPositions[1],passAirportPositions[3]},
            {crossroadsPositions[0], crossroadsPositions[1]},
            {crossroadsPositions[0], crossroadsPositions[1], crossroadsPositions[2], passAirportPositions[3]},
            {passAirportPositions[0], passAirportPositions[2], passAirportPositions[5], crossroadsPositions[2]},
            {crossroadsPositions[1], crossroadsPositions[2], crossroadsPositions[3], passAirportPositions[6]},
            {passAirportPositions[3], crossroadsPositions[2], crossroadsPositions[3]},
            {passAirportPositions[4], crossroadsPositions[3]}
    };



    public static Position[] getPassAirportPositions() {
        return passAirportPositions;
    }

    public static Position[] getMiliAirportPositions() {
        return miliAirportPositions;
    }

    public static Position[] getCrossroadsPositions() {
        return crossroadsPositions;
    }

    public static Position[][] getPassConnections() {
        return passConnections;
    }

    public static int getPathWidth() {
        return pathWidth;
    }

    public static BufferedImage getPassAirportImg() {
        return passAirportImg;
    }

}
