import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class WorldController extends JPanel {

    private static WorldController worldController;
    private static WorldInspector worldInspector;
    private static Map mainMap;
    private Rectangle rect;

    private PhysicalObject selected;

    public PhysicalObject getSelected() {
        return selected;
    }

    public void setSelected(PhysicalObject selected) {
        this.selected = selected;
    }

    public static WorldController getWorldController() {
        return worldController;
    }

    public WorldController() {

        JFrame mainFrame = new JFrame("JavaWorld");

        mainFrame.add(this);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setSize(800, 800);

        mainFrame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //setSelected(new Position(e.getX(), e.getY()));
                worldInspector.setLabel1(new Integer(e.getX()).toString());
                worldInspector.setLabel2(new Integer(e.getY()).toString());
                for (PhysicalObject object:mainMap.getObjectsToDraw()) {
                    rect = null;
                    if(object.getImage() != null) {
                        rect = new Rectangle((int) object.getPosition().getX() - object.getImage().getWidth() / 2,
                                (int) object.getPosition().getY() - object.getImage().getHeight() / 2,
                                object.getImage().getWidth(),
                                object.getImage().getHeight());

//                        System.out.println(object.toString());
                        if (rect.contains(e.getX(), e.getY() - 24)) {
                            setSelected(object);
                            System.out.println(object.getPosition().toString());
                            break;
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        mainFrame.setVisible(true);
        infiniteLoop();

    }

    public void infiniteLoop() {
        Runnable r = () -> {
            while (true) {
                repaint();
                //System.out.println("REPAINT");
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < mainMap.getObjectsToDraw().size(); i++) {
            mainMap.getObjectsToDraw().get(i).drawImage(g);
        }
        if(rect != null){
            g.drawRect(rect.x, rect.y, rect.height, rect.width);
        }
    }

    public static void main(String args[]) {
        mainMap = new Map();
        worldController = new WorldController();
        worldInspector = new WorldInspector();
        worldInspector.spawnPlane();
        for (int i = 0; i < mainMap.getObjectsToDraw().size(); i++) {
            System.out.println(mainMap.getObjectsToDraw().get(i).toString());
            System.out.println(mainMap.getObjectsToDraw().get(i).getPosition().toString());
        }

    }

    public static Map getMainMap() {
        return mainMap;
    }
}