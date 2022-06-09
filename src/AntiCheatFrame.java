import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AntiCheatFrame {
    private final int WIDTH = 700;
    private final int HEIGHT = 400;
    private AntiCheat antiCheat;
    private JFrame main_frame;
    private JPanel main_panel;
    public AntiCheatFrame(String rootPath, String referenceFile ){
        antiCheat = new AntiCheat(rootPath, referenceFile);
    }
    public void initWindow(){
        //JFrame initialization
        main_frame = new JFrame("AntiCheat-Jar");
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setSize(WIDTH, HEIGHT);
        main_frame.setResizable(false);
        main_frame.setVisible(true);

        //Main panel initialization
        main_panel = new JPanel();
        main_panel.setSize(new Dimension(WIDTH, HEIGHT));
        main_frame.add(main_panel);

        //Setting background color
        Color color = new Color(0,25,162);
        main_panel.setBackground(color);

        //Adding two buttons
        JButton b = new JButton("Obtain Reference Files");
        JButton b2 = new JButton("Check File Integrity");
        main_panel.add(b);
        main_panel.add(b2);

    }


}
