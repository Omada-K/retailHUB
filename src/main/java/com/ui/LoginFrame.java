package com.ui;

import com.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends JFrame {
  private JPanel loginPanel;
  private JTextField inputUsername;
  private JTextField inputPassword;
  private JButton btnLogin;
  private JButton cancelButton;



  public LoginFrame (MainFrame mainFrame) {
    setContentPane(loginPanel);// don't forget this, the window will be empty
    setVisible(true);
    setResizable(true);
    setSize(600, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    //create a listener
    ActionListener OnClick = new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        //TASK work here to create auth logic
        String useremail = getInputUsername().getText();
        String password = getInputPassword().getText();
        System.out.println("Username: " + useremail);
        System.out.println("Password: " + password);

        Auth_arrays creds = new Auth_arrays(useremail,password); //gets userinput and password input

        User user1 = new User("admin2","admin@","2524"); //creates a user from the User class
        //adds a user in the username list and password list - hard code
        creds.auth_add_user_in_list("admin","123");
        creds.auth_add_user_in_list("pa","ff");
        //adds a user (name,password) in the username list and password list in the AUTH class, using the User class
        creds.auth_add_user_in_list(user1.getEmail(),user1.getUserPassword());


        if (creds.auth_check()) {
          mainFrame.setVisible(true);
          dispose();//this makes THIS frame not visible
        }
        else {
          System.out.println("Ti kaneis ekei atimouli?"); }
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

