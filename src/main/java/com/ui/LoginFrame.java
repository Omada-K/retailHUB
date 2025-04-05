package com.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{
  private JPanel loginPanel;
  private JTextField inputUsername;
  private JTextField inputPassword;
  private JButton btnLogin;
  private JButton cancelButton;

  public LoginFrame(){
    setVisible(true);
    setResizable(true);
    setLayout(null);// border layout by default.
    btnLogin.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){

        String txt = getInputUsername().getText();
        System.out.println(txt);
      }
    });
  }

  public JPanel getLoginPanel(){
    return loginPanel;
  }

  public JTextField getInputUsername(){
    return inputUsername;

  }

}

