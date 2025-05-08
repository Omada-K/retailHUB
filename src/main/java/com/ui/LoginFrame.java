package com.ui;

import com.dao.UserDAO;
import com.model.User;

import javax.swing.*;
import java.awt.*;
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
  private JLabel welcomeLabel;

  public LoginFrame (AppState appState) throws SQLException {
    setContentPane(loginPanel);// don't forget this, the window will be empty
    setVisible(true);
    setResizable(true);
    setSize(600, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    //by making btnLogin default btn "ENTER" activates it
    getRootPane().setDefaultButton(btnLogin);

    //font changes
    Font currentFont = welcomeLabel.getFont();
    welcomeLabel.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 24));

    //create a button listener
    ActionListener OnClick = new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        authenticate(appState);
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

  private void authenticate (AppState appState) {
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

    //auth
    boolean usermatch = false;
    for (User user : users) {
      if (user.getEmail().equals(userEmail)) {
        usermatch = true;
        if (user.getUserPassword().equals(password)) {
          appState.loggedInUserName = user.getName();
          new MainFrame(appState).setVisible(true);
          dispose();//this makes THIS frame not visible
        } else {
          System.out.println("Wrong password");
        }
      }
    }
    if (!usermatch) {
      System.out.println("User doesn't match");
    }
  }
}

