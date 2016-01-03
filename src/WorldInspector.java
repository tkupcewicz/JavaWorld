import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tymek on 31.12.2015.
 */
public class WorldInspector {
    private JPanel panel1;
    private JLabel Label1;
    private JLabel Label2;
    private JButton button1 = new JButton("Spawn Plane");

    public void setLabel1(String s) {
        Label1.setText(s);
    }

    public void setLabel2(String s) {
        Label2.setText(s);
    }

    public WorldInspector() {

        JFrame frame = new JFrame("World Inspector");
        frame.setContentPane(this.panel1);
        frame.setSize(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public void spawnPlane() {
        System.out.println("Button pressed");
        Runnable r = new PassengerAircraft();
        Thread t = new Thread(r);
        t.start();
    }

    public static void main(String[] args){

    }
}

