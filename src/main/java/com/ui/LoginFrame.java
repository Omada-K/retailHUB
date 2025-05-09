package com.ui;

import com.dao.UserDAO;
import com.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginFrame extends BaseFrame {
  private JPanel loginPanel;
  private JTextField inputUsername;
  private JPasswordField inputPassword;
  private JButton btnLogin;
  private JButton exitButton;
  private JLabel welcomeLabel;

  public LoginFrame (AppState appState) throws SQLException {
    super();
    setContentPane(loginPanel);// don't forget this, the window will be empty
    setTitle("Welcome! Please Login");
    setSize(600, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
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
          JOptionPane.showMessageDialog(loginPanel, "Wrong password!");
//          System.out.println("Wrong password");
        }
      }
    }
    if (!usermatch) {
      JOptionPane.showMessageDialog(loginPanel, "User doesn't match!");
//      System.out.println("User doesn't match");
    }
  }
}

