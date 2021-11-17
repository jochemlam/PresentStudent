package PresentStudentGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MenuScherm {
    private JButton berekenButton;
    private JTextField Getal1;
    private JTextField Getal2;
    private JPanel MainView;
    private JButton ROOSTERButton;
    private JButton ABSENTButton;
    private JButton SCANButton;
    private JLabel editthis;
    private JLabel Uitkomst;

    public static JFrame homeScreen;
    public static JFrame roosterScreen;
    public static JFrame absentScreen;

    public MenuScherm() {
        SCANButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //code hier
                System.out.println("scan knop ingedrukt.");
            }
        });
        ROOSTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // code voor roosterknop
                homeScreen.setVisible(false);
                roosterScreen.setVisible(true);
                absentScreen.setVisible(false);
                System.out.println("Rooster geopent");
            }
        });
        ABSENTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                homeScreen.setVisible(false);
                roosterScreen.setVisible(false);
                absentScreen.setVisible(true);
                System.out.println("AbsentScherm geopent");
            }
        });
    }

    public static void main(String[] args) {
        createHomeScreen();
        createRoosterScreen();
        createAbsentScreen();

    homeScreen.setVisible(true);
    roosterScreen.setVisible(false);
    absentScreen.setVisible(false);
    }

    public static void createHomeScreen() {
        JFrame frame = new JFrame("HomeScreen");
        frame.setContentPane(new MenuScherm().MainView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        homeScreen = frame;
    }
    public static void createRoosterScreen() {
        JFrame frame = new JFrame("RoosterScreen");
        frame.setContentPane(new MenuScherm().MainView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        roosterScreen = frame;
    }
    public static void createAbsentScreen() {
        JFrame frame = new JFrame("AbsentScreen");
        frame.setContentPane(new MenuScherm().MainView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        absentScreen = frame;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

