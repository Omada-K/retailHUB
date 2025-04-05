package com.ui;

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
        String txt = getInputUsername().getText();
        System.out.println(txt);

        dispose();//this makes THIS frame not visible
        mainFrame.setVisible(true);
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

}

