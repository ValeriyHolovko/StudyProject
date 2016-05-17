package view;

import controller.*;
import model.Admin;
import model.AppDB;
import model.User;
import model.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by 1 on 01.11.2015.
 */
public class LoginFrame extends JFrame{

    private AppDB appDB;
    private ILoginController loginController;

    private JTextField loginField;
    private JPasswordField passField;
    private JButton enterButton;
    private JButton registerButton;

    public LoginFrame(AppDB appDB, ILoginController loginController){

        this.appDB = appDB;
        this.loginController = loginController;

        setLocationRelativeTo(null);
        setSize(250, 150);
        setTitle("Authorization");
        init();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void init() {

        JLabel loginLabel = new JLabel("Login: ");
        JLabel passwordLabel = new JLabel("Password: ");

        loginField = new JTextField();
        passField = new JPasswordField();

        enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String login = loginField.getText();
                String password = String.valueOf(passField.getPassword());

                UserEntity user = null;
                ArrayList<UserEntity> users = appDB.getUsers();
                for (UserEntity tempUser : users) {
                    if (tempUser.getLogin().equals(login) && tempUser.getPassword().equals(password)){
                        user = tempUser;
                        break;
                    }
                }

                if (user == null){
                    JOptionPane.showMessageDialog(LoginFrame.this,"Wrong login or password.","Error",JOptionPane.ERROR_MESSAGE);
                } else if (user instanceof Admin){
                    IAdminController adminController = new AdminController(appDB);
                    new AdminFrame((Admin) user, adminController);
                } else if (user instanceof User){
                    IUserController userController = new UserController(appDB,(User) user);
                    new UserFrame((User) user, userController);
                }
            }
        });

        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new RegisterFrame();

            }
        });


        JPanel northInputPanel = new JPanel(new GridLayout(2,2));
        northInputPanel.add(loginLabel);
        northInputPanel.add(loginField);
        northInputPanel.add(passwordLabel);
        northInputPanel.add(passField);

        JPanel southInputPanel = new JPanel(new GridLayout(1,2));
        southInputPanel.add(enterButton);
        southInputPanel.add(registerButton);

        getContentPane().add(northInputPanel, BorderLayout.NORTH);
        getContentPane().add(southInputPanel, BorderLayout.SOUTH);

    }


    private class RegisterFrame extends JFrame {

        public RegisterFrame(){

            setLocationRelativeTo(LoginFrame.this);
            setSize(250,200);
            setTitle("Registration");
            init();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        }

        private void init() {

            JLabel loginLabel = new JLabel("Login: ");
            JLabel passwordLabel = new JLabel("Password: ");
            JLabel nameLabel = new JLabel("Name: ");
            JLabel surnameLabel = new JLabel("Surname: ");
            JLabel phoneLabel = new JLabel("Phone: ");

            JTextField loginField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JTextField nameField = new JTextField();
            JTextField surnameField = new JTextField();
            JTextField phoneField = new JTextField();

            JButton registrationButton = new JButton("Register");
            registrationButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        loginController.register(loginField.getText(), passwordField.getPassword().toString(), nameField.getText(), surnameField.getText(), Long.parseLong(phoneField.getText()) );

                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Phone field must contain 0-9", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException e2) {
                        JOptionPane.showMessageDialog(LoginFrame.this, e2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });


            JPanel northInputPanel = new JPanel(new GridLayout(5,2));

            northInputPanel.add(loginLabel);
            northInputPanel.add(loginField);
            northInputPanel.add(passwordLabel);
            northInputPanel.add(passwordField);
            northInputPanel.add(nameLabel);
            northInputPanel.add(nameField);
            northInputPanel.add(surnameLabel);
            northInputPanel.add(surnameField);
            northInputPanel.add(phoneLabel);
            northInputPanel.add(phoneField);

            getContentPane().add(northInputPanel, BorderLayout.NORTH);
            getContentPane().add(registrationButton, BorderLayout.SOUTH);

        }

    }
}
