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
  private ArrayList<User> usernameList; //NADOUME AUTO - alex
  private ArrayList<User> passwordList;//NADOUME AUTO - alex

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
        String username = getInputUsername().getText();
        String password = getInputPassword().getText();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        Auth creds = new Auth(username,password,usernameList,passwordList); //NADOUME AUTO - alex

        dispose();//this makes THIS frame not visible
        if (creds.Checker())
          mainFrame.setVisible(true);
        else
          System.out.println("Ti kaneis ekei atimouli?");
        creds.authentication_message();
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

