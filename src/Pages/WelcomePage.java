package Pages;

import javax.swing.*;
import java.awt.*;

public class WelcomePage {
    JFrame frame = new JFrame("Welcome");
    JLabel welcomeLabel = new JLabel("Hello");

    WelcomePage(String userID){

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        welcomeLabel.setFont(new Font(null, Font.PLAIN, 25));
        welcomeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        welcomeLabel.setVerticalTextPosition(SwingConstants.CENTER);
        welcomeLabel.setText("Welcome, " + userID);

        frame.add(welcomeLabel);
    }
}
