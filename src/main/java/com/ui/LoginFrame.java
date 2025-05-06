package com.ui;

import com.dao.UserDAO;
import com.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginFrame extends JFrame {
  private JPanel loginPanel;
  private JTextField inputUsername;
  private JTextField inputPassword;
  private JButton btnLogin;
  private JButton exitButton;

  public LoginFrame (AppState appState) throws SQLException {
    setContentPane(loginPanel);// don't forget this, the window will be empty
    setVisible(true);
    setResizable(true);
    setSize(600, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    //create a listener
    ActionListener OnClick = new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        ArrayList<User> users = null;
        try {
          users = UserDAO.getData();
        } catch (SQLException ex) {
          throw new RuntimeException(ex);
        }
        //TASK work here to create auth logic
        String userEmail = inputUsername.getText();
        String password = inputPassword.getText();
        System.out.println("Username: " + userEmail);
        System.out.println("Password: " + password);

        //new auth
        for (User user : users) {
          if (user.getEmail().equals(userEmail)) {
            if (user.getUserPassword().equals(password)) {
              appState.mainFrame.setVisible(true);
              appState.loggedInUserName = user.getName();
              dispose();//this makes THIS frame not visible
            } else {
              System.out.println("Wrong password");
            }
          } else {
            System.out.println("User doesn't match");
          }
        }
      }
    };
    //connect listener to the button
    btnLogin.addActionListener(OnClick);

    //exit button
    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        dispose();
      }
    });
  }
}

