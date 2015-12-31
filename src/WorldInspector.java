import javax.swing.*;
import java.awt.*;

/**
 * Created by Tymek on 31.12.2015.
 */
public class WorldInspector {
    private JPanel panel1;
    private JLabel Label1;
    private JLabel Label2;

    public void setLabel1(String s) {
        Label1.setText(s);
    }

    public void setLabel2(String s) {
        Label2.setText(s);
    }

    public WorldInspector() {

        JFrame frame = new JFrame("VisualizationManagerWindow");
        frame.setContentPane(this.panel1);
        frame.setSize(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

