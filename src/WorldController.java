import oracle.jvm.hotspot.jfr.JFR;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class WorldController extends JPanel {

    static private WorldController worldController;
    static private WorldInspector worldInspector;

    private Position selected;

    public Position getSelected() {
        return selected;
    }

    public void setSelected(Position selected) {
        this.selected = selected;
    }

    public WorldController() {
        JFrame mainFrame = new JFrame("JavaWorld");
        mainFrame.add(this);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setSize(800, 800);

        mainFrame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelected(new Position(e.getX(),e.getY()));
                worldInspector.setLabel1(new Integer(e.getX()).toString());
                worldInspector.setLabel2(new Integer(e.getY()).toString());
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
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    private static Map mainMap;

    @Override
    public void paint(Graphics g){


        //TODO
        //repaint w infinitowej lupie

        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < mainMap.getPassengerAirports().length; i++){
            for(int j = 0; j < mainMap.getPassengerAirports()[i].getConnections().size(); j++){
                final Path tempPath = Path.calcuatePath(mainMap.getPassengerAirports()[i].getPosition(),
                        mainMap.getPassengerAirports()[i].getConnections(j).getPosition());

                g2d.drawLine(tempPath.getOrigin().getX(),
                        tempPath.getOrigin().getY(),
                        tempPath.getDestination().getX(),
                        tempPath.getDestination().getY());

            }
            g2d.drawImage(mainMap.getPassengerAirports()[i].getImage(),
                    mainMap.getPassengerAirports()[i].getPosition().getX() - MapConfig.getPassAirportImg().getWidth()/2,
                    mainMap.getPassengerAirports()[i].getPosition().getY() - MapConfig.getPassAirportImg().getHeight()/2,
                    this);
        }
        for(int i = 0; i < mainMap.getMilitaryAirports().length; i++){
            //g2d.drawImage();
            g2d.drawOval(mainMap.getMilitaryAirports()[i].getPosition().getX() -25,
                    mainMap.getMilitaryAirports()[i].getPosition().getY() -25, 50,50);
            for(int j = 0; j < mainMap.getMilitaryAirports()[i].getConnections().size(); j++){
                final Path tempPath = Path.calcuatePath(mainMap.getMilitaryAirports()[i].getPosition(),
                        mainMap.getMilitaryAirports()[i].getConnections(j).getPosition());

                g2d.drawLine(tempPath.getOrigin().getX(),
                        tempPath.getOrigin().getY(),
                        tempPath.getDestination().getX(),
                        tempPath.getDestination().getY());
            }
        }
        for(int i = 0; i < mainMap.getCrossroadsArray().length; i++){
            g2d.fillRect(mainMap.getCrossroadsArray()[i].getPosition().getX() - 25,
                    mainMap.getCrossroadsArray()[i].getPosition().getY() - 25, 50,50);

            for(int j = 0; j < mainMap.getCrossroadsArray()[i].getConnections().size(); j++){
                final Path tempPath = Path.calcuatePath(mainMap.getCrossroadsArray()[i].getPosition(),
                        mainMap.getCrossroadsArray()[i].getConnections(j).getPosition());

                g2d.drawLine(tempPath.getOrigin().getX(),
                        tempPath.getOrigin().getY(),
                        tempPath.getDestination().getX(),
                        tempPath.getDestination().getY());
            }
        }



    }


    public static void main(String args[]){
        mainMap = new Map();

        worldController = new WorldController();
        worldInspector = new WorldInspector();


    }

}