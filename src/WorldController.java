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
        for(int i= 0; i < mainMap.getPassengerAirports().length; i++){
            g2d.drawImage(mainMap.getPassengerAirports()[i].getImage(), mainMap.getPassengerAirports()[i].getPosition().getX(), mainMap.getPassengerAirports()[i].getPosition().getY(), this);
            //g2d.fillOval(mainMap.getPassengerAirports()[i].getPosition().getX(), mainMap.getPassengerAirports()[i].getPosition().getY(), 30, 30);
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