package view;

import controller.IAdminController;
import model.Admin;
import model.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

/**
 * Created by Valeriy Holovko on 16.05.2016.
 */
public class AdminFrame extends JFrame {

    private Admin user;
    private IAdminController adminController;

    private JTextArea outputArea;
    private JButton findUserButton;
    private JButton findAdvertisementsButton;
    private JButton signOutButton;
    private JPanel southButtonsPanel;

    public AdminFrame(Admin user, IAdminController adminController) {
        this.user = user;
        this.adminController = adminController;

        setLocationRelativeTo(null);
        setSize(500, 500);
        setTitle(String.format("Admin: %s",user.getLogin()));
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init() {

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        findUserButton = new JButton("Find User");
        findUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new FindUserFrame();
            }
        });

        southButtonsPanel = new JPanel(new GridLayout(1,3));
        southButtonsPanel.add(findUserButton);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private class FindUserFrame extends JFrame{

        private JPanel northPanel;
        private JPanel southPanel;

        public FindUserFrame(){

            setLocationRelativeTo(AdminFrame.this);
            setSize(250,150);
            setTitle("Search");
            init();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        }

        private void init() {

            JLabel loginLabel = new JLabel("Login: ");
            JTextField loginField = new JTextField();

            JLabel iDLabel = new JLabel("ID: ");
            JTextField iDField = new JTextField();

            JRadioButton byIDButton = new JRadioButton("By ID");
            JRadioButton byLoginButton = new JRadioButton("By login");
            JRadioButton allButton = new JRadioButton("Get all");



            byIDButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    byLoginButton.setSelected(false);
                    allButton.setSelected(false);
                }
            });
            byLoginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    byIDButton.setSelected(false);
                    allButton.setSelected(false);

                }
            });
            allButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    byIDButton.setSelected(false);
                    byLoginButton.setSelected(false);
                }
            });


            JButton findButton = new JButton("Find");
            findButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (allButton.isSelected()){

                        StringBuilder sb = new StringBuilder();
                        for (UserEntity user : adminController.getAllUsers()) {
                            sb.append(user.toString());
                        }
                        outputArea.setText(sb.toString());
                        FindUserFrame.this.hide();

                    } else if (byIDButton.isSelected()){

                        try {
                            Integer id = Integer.parseInt(iDField.getText());
                            outputArea.setText(adminController.getUserByID(id).toString());
                            FindUserFrame.this.hide();
                        } catch (NumberFormatException e1){
                            JOptionPane.showMessageDialog(FindUserFrame.this, "ID must contain 0-9", "ERROR", JOptionPane.ERROR_MESSAGE);
                        } catch (NoSuchElementException e2){
                            JOptionPane.showMessageDialog(FindUserFrame.this, e2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }

                    } else if (byLoginButton.isSelected()){

                        try {
                            String login = loginField.getText();
                            outputArea.setText(adminController.getUserByLogin(login).toString());
                        } catch (NoSuchElementException e2){
                            JOptionPane.showMessageDialog(FindUserFrame.this, e2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(FindUserFrame.this, "Select item", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });


            northPanel = new JPanel(new GridLayout(1,3));
            northPanel.add(byIDButton);
            northPanel.add(byLoginButton);
            northPanel.add(allButton);

            southPanel = new JPanel(new GridLayout(2,2));
            southPanel.add(iDLabel);
            southPanel.add(iDField);
            southPanel.add(loginLabel);
            southPanel.add(loginField);

            getContentPane().add(southPanel, BorderLayout.CENTER);
            getContentPane().add(northPanel, BorderLayout.NORTH);
            getContentPane().add(findButton, BorderLayout.SOUTH);


        }
    }


}
