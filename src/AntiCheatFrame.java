import DatabaseAPI.DatabaseOperation;
import DatabaseAPI.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class AntiCheatFrame {
    private final int WIDTH = 800;
    private final int HEIGHT = 500;
    private AntiCheat antiCheat;
    private JFrame main_frame;
    private JPanel main_panel;

    private JFrame login_frame;

    private JPanel login_panel;
    DatabaseOperation testDatabase = DatabaseOperation.getInstance("jdbc:sqlserver://poromtest.mssql.somee.com;database=poromtest;user=PoromK_SQLLogin_1;password=prnclvbss7;encrypt=true;trustServerCertificate=true;loginTimeout=30;");
    public AntiCheatFrame(String rootPath, String referenceFile ){
        antiCheat = new AntiCheat(rootPath, referenceFile);
    }
    public void initWindow(){
        //JFrame initialization
        main_frame = new JFrame("AntiCheat-Jar");
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setSize(600, 400);
        main_frame.setResizable(false);
        main_frame.setVisible(true);

        //Main panel initialization
        main_panel = new JPanel();
        main_panel.setSize(new Dimension(600, 400));
        main_frame.add(main_panel);

        //Setting background color
        Color color = new Color(3, 159, 3);
        main_panel.setBackground(color);

        //Adding two buttons
        JButton reference = new JButton("Obtain Reference Files");
        JButton integrity = new JButton("Check File Integrity");
        main_panel.add(reference);
        main_panel.add(integrity);

        //Functionality for button 1
        reference.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AntiCheat antiCheat = new AntiCheat(
                        "C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\Nuntu",
                        "referenceTest.txt");
                try {
                    antiCheat.start();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Functionality for button 2
        integrity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AntiCheat antiCheat = new AntiCheat(
                        "C:\\Users\\Farhan\\IdeaProjects\\anticheat-jar\\Nuntu",
                        "referenceTest.txt");
                try {
                    ArrayList<String> foundPaths = antiCheat.start2();
                    //Verified
                    if (foundPaths.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Verified", "Popup",JOptionPane.INFORMATION_MESSAGE);
                    }
                    //Verification failed and output files that were edited
                    else{
                        JOptionPane.showMessageDialog(null, "Failed Verification\n" + foundPaths, "Popup",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    public void NewWindow() throws IOException {
        DatabaseOperation testDatabase = DatabaseOperation.getInstance("jdbc:sqlserver://poromtest.mssql.somee.com;database=poromtest;user=PoromK_SQLLogin_1;password=prnclvbss7;encrypt=true;trustServerCertificate=true;loginTimeout=30;");
        login_frame = new JFrame("AntiCheat-Jar");
        login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login_frame.setSize(600, 400);
        login_frame.setResizable(false);
        login_frame.setVisible(true);

        //Main panel initialization
        login_panel = new JPanel();
        login_panel.setSize(new Dimension(600, 400));
        login_panel.setLayout(null);
        login_frame.add(login_panel);

        //Setting background color
        Color color = new Color(3, 159, 3);
        login_panel.setBackground(color);

        //login and register button
        JButton login_button = new JButton("Login");
        login_button.setBounds(325,200,100,25);
        JButton register_button = new JButton("Register");
        register_button.setBounds(200,200,100,25);

        login_panel.add(login_button);
        login_panel.add(register_button);

        //username and password labels
        JLabel username_label = new JLabel("Username: ");
        username_label.setBounds(200,125,75,25);

        JLabel password_label = new JLabel("Password: ");
        password_label.setBounds(200,150,75,25);

        login_panel.add(username_label);
        login_panel.add(password_label);


        //username and password text fields
        JTextField username_text = new JTextField(100);
        JTextField password_text = new JTextField(100);
        username_text.setBounds(275,125,75,25);
        password_text.setBounds(275,150,75,25);
        username_text.setSize(100,25);
        password_text.setSize(100,25);

        //information from each text field
        String username_info = username_text.getText();
        String password_info = password_text.getText();


        login_panel.add(username_text);
        login_panel.add(password_text);

        //attach functionality to buttons and textfields
        register_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Go to register page
                registerWindow();
                login_frame.setVisible(false);
            }
        });
        login_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if login successful then login
                initWindow();
                login_frame.setVisible(false);
                //else throw error message
            }
        });
        testDatabase.close();
    }

    public void registerWindow(){
        JFrame register_frame = new JFrame("AntiCheat-Jar");
        register_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        register_frame.setSize(600, 400);
        register_frame.setResizable(false);
        register_frame.setVisible(true);

        //Main panel initialization
        JPanel register_panel = new JPanel();
        register_panel.setSize(new Dimension(600, 400));
        register_panel.setLayout(null);
        register_frame.add(register_panel);

        //Setting background color
        Color color = new Color(3, 159, 3);
        register_panel.setBackground(color);

        //login and register button
        JButton register_button = new JButton("Register");
        register_button.setBounds(250,225,100,25);

        register_panel.add(register_button);

        //username and password labels
        JLabel username_label = new JLabel("Email: ");
        username_label.setBounds(225,125,75,25);

        JLabel password_label = new JLabel("Password: ");
        password_label.setBounds(198,150,75,25);

        JLabel confirm_label = new JLabel("Confirm Password: ");
        confirm_label.setBounds(150,175,125,25);

        register_panel.add(username_label);
        register_panel.add(password_label);
        register_panel.add(confirm_label);


        //username and password text fields
        JTextField username_text = new JTextField();
        JTextField password_text = new JTextField();
        JTextField confirm_text = new JTextField();
        username_text.setBounds(275,125,75,25);
        password_text.setBounds(275,150,75,25);
        confirm_text.setBounds(275,175,75,25);
        username_text.setSize(100,25);
        password_text.setSize(100,25);
        confirm_text.setSize(100,25);

        //information from each text field
        String username_info = username_text.getText();
        String password_info = password_text.getText();
        String confirm_info = confirm_text.getText();


        register_panel.add(username_text);
        register_panel.add(password_text);
        register_panel.add(confirm_text);
    }


}
