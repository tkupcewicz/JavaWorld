import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Tymek on 30.12.2015.
 */
class Crossroad extends PhysicalObject {
    /**
     * Constructor of Crossroad at given position x and y
     * @param posX position
     * @param posY position
     */
    public Crossroad(float posX, float posY) {
        this.setPosition(posX, posY);
        this.img = MapConfig.getCrossroadImg();
    }

    /**
     * Draws image of crossroad if checked in Control Panel
     * @param g graphic
     */
    @Override
    void drawImage(Graphics g) {
        if (MapConfig.isCrossroadVisible()) {
            g.drawImage(
                    MapConfig.getCrossroadImg(),
                    (int) this.getPosition().getX() - MapConfig.getCrossroadImg().getWidth() / 2,
                    (int) this.getPosition().getY() - MapConfig.getCrossroadImg().getHeight() / 2,
                    WorldController.getWorldController());
        }
    }

    /**
     *
     * @return returns image of Crossroad
     */
    @Override
    BufferedImage getImage() {
        return MapConfig.getCrossroadImg();
    }

    /**
     * Hides building inspector when crossroad is selected
     */
    @Override
    void inspect() {
        WorldController.getBuildingInspector().getFrame().setVisible(false);
    }

}
