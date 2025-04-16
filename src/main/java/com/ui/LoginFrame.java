package com.ui;

import com.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginFrame extends JFrame {
  private JPanel loginPanel;
  private JTextField inputUsername;
  private JTextField inputPassword;
  private JButton btnLogin;
  private JButton cancelButton;
  //new auth
  private ArrayList<User> users = new ArrayList<User>();
  private User user1 = new User("Mitsos", "mitsos@gmail.com", "123456");
  private User user2 = new User("Anna", "anna@gmail.com", "000000");
  private User user3 = new User("Mastoras", "mastoras@gmail.com", "aaabbb");

  public LoginFrame (MainFrame mainFrame) {
    setContentPane(loginPanel);// don't forget this, the window will be empty
    setVisible(true);
    setResizable(true);
    setSize(600, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    users.add(user1);
    users.add(user2);
    users.add(user3);

    //create a listener
    ActionListener OnClick = new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        //TASK work here to create auth logic
        String userEmail = getInputUsername().getText();
        String password = getInputPassword().getText();
        System.out.println("Username: " + userEmail);
        System.out.println("Password: " + password);

        //new auth
        for (User user : users) {
          if (user.getEmail().equals(userEmail)) {
            if (user.getUserPassword().equals(password)) {
              mainFrame.setVisible(true);
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
  }

  //getters and setters
  public JPanel getLoginPanel () {
    return loginPanel;
  }

  public JTextField getInputUsername () {
    return inputUsername;

  }

  public JTextField getInputPassword () {
    return inputPassword;
  }

}

