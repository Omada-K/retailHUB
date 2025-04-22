package com.ui;

import com.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserForm extends JFrame {
  private JButton cancelButton;
  private JButton saveButton;
  private JTextField emailInput;
  private JTextField passwordInput;
  private JTextField nameInput;
  private JTextField confirmPasswordInput;
  private JCheckBox isManagerCheckBox;
  private JPanel formPanel;

  //Edit form(needs user)
  public UserForm (AppState appState, User user) {
    setContentPane(formPanel);
    setVisible(true);
    setResizable(true);
    setSize(480, 320);
    setLocationRelativeTo(null);

    nameInput.setText(user.getName());
    emailInput.setText(user.getEmail());
    passwordInput.setText(user.getUserPassword());
    confirmPasswordInput.setText(user.getUserPassword());

    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        dispose();
      }
    });
  }

  //Create user(no user required)
  public UserForm (AppState appState) {
    setVisible(false);
    setResizable(true);
    setSize(480, 320);

    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        dispose();
      }
    });
  }
}
