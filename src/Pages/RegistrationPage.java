package Pages;

import Data.IDandPasswords;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;


public class RegistrationPage implements ActionListener {
    Font font = new Font("Century Gothic", Font.PLAIN, 14);
    FileWriter writer = new FileWriter("src/Data/userData.txt", true);
    File userData = new File("src/Data/userData.txt");
    BufferedImage image = ImageIO.read(new File("src/Pictures/register_back.jpg"));

    JPasswordField userPasswordField = new JPasswordField();
    IDandPasswords idandPasswords = new IDandPasswords();
    LoginPage loginPage;

    HashMap<String, String> logininfo;

    JFrame register = new JFrame("Registration");
    JButton registerButton = new JButton("Register");
    JButton backButton = new JButton("Back");
    JLabel warningLabel = new JLabel();
    JLabel userIDLabel = new JLabel("Username");
    JLabel userPasswordLabel = new JLabel("Password");
    JLabel messageLabel = new JLabel("CREATE NEW ACCOUNT");
    JLabel background;
    JTextField userIDField = new JTextField();

    RegistrationPage(HashMap<String, String> loginInfoOriginal) throws IOException {

        logininfo = loginInfoOriginal;

        /////  LABELS  ////////
        messageLabel.setBounds(100, 12, 250, 25);
        messageLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));

        warningLabel.setBounds(117, 35, 250, 25);
        warningLabel.setFont(new Font("Century Gothic", Font.BOLD, 13));
        warningLabel.setForeground(Color.red);

        userIDLabel.setBounds(40, 60, 75, 25);
        userIDLabel.setFont(font);
        userPasswordLabel.setBounds(45, 110, 75, 25);
        userPasswordLabel.setFont(font);

        background = new JLabel(new ImageIcon(image));
        background.setSize(400, 220);

        //////  FIELDS  //////
        userIDField.setBounds(120, 60, 200, 25);
        userIDField.setFont(font);
        userPasswordField.setBounds(120, 110, 200, 25);
        userPasswordField.setFont(font);

        //////  BUTTONS  //////
        registerButton.setBounds(120, 150, 130, 35);
        registerButton.setFont(font);
        registerButton.setFocusable(false);
        registerButton.setOpaque(false);
        registerButton.addActionListener(this);

        backButton.setBounds(252, 150, 70, 35);
        backButton.setFont(font);
        backButton.setFocusable(false);
        backButton.setOpaque(false);
        backButton.addActionListener(this);

        ///////  ADD  ///////
        register.add(userIDField);
        register.add(userPasswordField);

        register.add(messageLabel);
        register.add(warningLabel);
        register.add(userIDLabel);
        register.add(userPasswordLabel);

        register.add(registerButton);
        register.add(backButton);
        register.add(background);

        ///////  FRAME  //////
        register.setSize(400, 250);
        register.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        register.setLocationRelativeTo(null);

        register.setLayout(null);
        register.setResizable(false);
        register.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registerButton) {

            if (!(userIDField.getText().isEmpty())){
                if (!(String.valueOf((userPasswordField.getPassword())).isEmpty())){
                    try {
                        String uID = userIDField.getText();
                        String uPass = String.valueOf(userPasswordField.getPassword());
                        if(Files.lines(Paths.get(userData.toURI())).noneMatch(k->k.contains(uID))){

                            writer.write(uID + "=" + uPass + "\n");
                            writer.close();

                            System.out.println("registered: " + userIDField.getText() + " = " + String.valueOf((userPasswordField.getPassword())));
                            register.dispose();

                            IDandPasswords idandPasswords = new IDandPasswords();
                            LoginPage loginPage = new LoginPage(idandPasswords.getLogininfo());
                        } else {
                            warningLabel.setText("Username already exists!");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    warningLabel.setBounds(117, 35, 250, 25);
                    warningLabel.setFont(new Font("Century Gothic", Font.BOLD, 13));
                    warningLabel.setForeground(Color.red);
                    warningLabel.setText("Username or password is empty!");
                }
            }
        }

        if(e.getSource() == backButton){
            register.dispose();
            try {
                loginPage = new LoginPage(idandPasswords.getLogininfo());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
