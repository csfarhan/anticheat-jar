import javax.swing.*;
import java.awt.*;

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
        main_frame = new JFrame("AntiCheat-Jar");
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setSize(WIDTH, HEIGHT);
        main_frame.setResizable(false);
        main_frame.setVisible(true);

        main_panel = new JPanel();
        main_panel.setSize(new Dimension(WIDTH, HEIGHT));


        JPanel testPanel = new JPanel();
        testPanel.setSize(30, 400);
        testPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        testPanel.setBackground(Color.BLACK);

        main_panel.add(testPanel);


    }


}
