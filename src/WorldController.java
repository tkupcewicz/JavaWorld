import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class WorldController extends JPanel {

    private static Map mainMap;
//    public WorldController(){
//
//
//    }

    //create a component that you can actually draw on.
    @Override
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < mainMap.getPassengerAirports().length; i++){
            for(int j = 0; j < mainMap.getPassengerAirports()[i].getConnections().size(); j++){
                final Path tempPath = Path.calcuatePath(mainMap.getPassengerAirports()[i].getPosition(), (Position) mainMap.getPassengerAirports()[i].getConnections().get(j));
                //g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(tempPath.getOrigin().getX(), tempPath.getOrigin().getY(), tempPath.getDestination().getX(), tempPath.getDestination().getY());

            }
            g2d.drawImage(mainMap.getPassengerAirports()[i].getImage(), mainMap.getPassengerAirports()[i].getPosition().getX() - MapConfig.getPassAirportImg().getWidth()/2, mainMap.getPassengerAirports()[i].getPosition().getY() - MapConfig.getPassAirportImg().getHeight()/2, this);
        }
        for(int i = 0; i < mainMap.getMilitaryAirports().length; i++){
            //g2d.drawImage();
            g2d.drawOval(mainMap.getMilitaryAirports()[i].getPosition().getX(), mainMap.getMilitaryAirports()[i].getPosition().getY(), 20,20);
        }
        for(int i = 0; i < mainMap.getCrossroadsArray().length; i++){
            g2d.drawRect(mainMap.getCrossroadsArray()[i].getPosition().getX(), mainMap.getCrossroadsArray()[i].getPosition().getY(), 20,20);
        }


    }


    public static void main(String args[]){
//        new WorldController();
        mainMap = new Map();

        JFrame mainFrame = new JFrame("JavaWorld");
        mainFrame.add(new WorldController());

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setSize(800, 800);

        mainFrame.setVisible(true);
    }

}