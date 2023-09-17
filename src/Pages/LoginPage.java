package Pages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class LoginPage implements ActionListener {

    Icon clear = new ImageIcon("src/Pictures/clear.jpg");
    Icon register = new ImageIcon("src/Pictures/register.jpg");
    Icon exit = new ImageIcon("src/Pictures/exitBtn2.png");
    Icon iconify = new ImageIcon("src/Pictures/iconifyBtn2.png");
    BufferedImage image = ImageIO.read(new File("src/Pictures/bPic3.jpg"));
    Font font = new Font("Century Gothic", Font.PLAIN, 14);
    HashMap<String, String> logininfo;

    JFrame frame = new JFrame("Login page");
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton(clear);
    JButton registerButton = new JButton(register);
    JButton exitButton = new JButton(exit);
    JButton iconifyButton = new JButton(iconify);
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("Username");
    JLabel userPasswordLabel = new JLabel("Password");
    JLabel messageLabel = new JLabel();
    JLabel background;

    public LoginPage(HashMap<String, String> loginInfoOriginal) throws IOException {

        logininfo = loginInfoOriginal;

        //////////////  LABELS  ////////////////
        userIDLabel.setBounds(40, 60, 75, 25);
        userIDLabel.setFont(font);
        userPasswordLabel.setBounds(45, 110, 75, 25);
        userPasswordLabel.setFont(font);

        messageLabel.setBounds(120, 28, 250, 35);
        messageLabel.setFont(new Font("Century Gothic", Font.BOLD, 13));

        background = new JLabel(new ImageIcon(image));
        background.setSize(400, 220);

        //////////////  FIELDS  ////////////////
        userIDField.setBounds(120, 60, 200, 25);
        userIDField.setFont(font);

        userPasswordField.setBounds(120, 110, 200, 25);
        userPasswordField.setFont(font);

        //////////////  BUTTONS  ////////////////
        loginButton.setBounds(120, 155, 120, 30);
        loginButton.setFont(font);
        loginButton.setFocusable(false);
        loginButton.setOpaque(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(255, 155, 30, 30);
        resetButton.setFont(font);
        resetButton.setFocusable(false);
        resetButton.setOpaque(false);
        resetButton.addActionListener(this);

        registerButton.setBounds(290, 155, 30, 30);
        registerButton.setFont(font);
        registerButton.setFocusable(false);
        registerButton.setOpaque(false);
        registerButton.addActionListener(this);

        exitButton.setBounds(359, 1, 40, 20);
        exitButton.setOpaque(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        iconifyButton.setBounds(317, 1, 40, 20);
        iconifyButton.setOpaque(false);
        iconifyButton.setBorderPainted(false);
        iconifyButton.setFocusable(false);
        iconifyButton.addActionListener(this);

        ///////////////  ADD  ////////////////
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);

        frame.add(userIDField);
        frame.add(userPasswordField);

        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(registerButton);
        frame.add(exitButton);
        frame.add(iconifyButton);

        frame.add(background);

        ///////////////  FRAME  ////////////////
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 220);
        frame.setUndecorated(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == loginButton){
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if(logininfo.containsKey(userID)){
                if(logininfo.get(userID).equals(password)){
                    frame.dispose();
                    WelcomePage welcomePage = new WelcomePage(userID);
                }else{
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Invalid username or password!");
                }
            }else{
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Invalid username or password!");
            }
        }

        if(e.getSource() == resetButton){
            userIDField.setText("");
            userPasswordField.setText("");
            messageLabel.setText("");
        }

        if(e.getSource() == registerButton){
            frame.dispose();
            try {
                RegistrationPage registrationPage = new RegistrationPage(logininfo);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getSource() == exitButton){
            System.exit(0);
        }

        if (e.getSource() == iconifyButton){
            frame.setState(Frame.ICONIFIED);
        }
    }
}
