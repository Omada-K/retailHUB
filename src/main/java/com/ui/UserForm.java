package com.ui;

import com.dao.UserDAO;
import com.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserForm extends BaseForm {
  private JButton cancelButton;
  private JButton saveButton;
  private JTextField emailInput;
  private JTextField passwordInput;
  private JTextField nameInput;
  private JTextField confirmPasswordInput;
  private JCheckBox isManagerCheckBox;
  private JPanel formPanel;

  //Edit form(needs user)
  public UserForm (TableModel content, Object userInput) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    User user = (User) userInput; //force generic object to be User

    int id = user.getId();
    nameInput.setText(user.getName());
    emailInput.setText(user.getEmail());
    passwordInput.setText(user.getUserPassword());
    confirmPasswordInput.setText(user.getUserPassword());

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        if (passwordInput.getText() != null &&//validation
                confirmPasswordInput.getText() != null &&
                nameInput.getText() != null &&
                emailInput.getText() != null &&
                passwordInput.getText().equals(confirmPasswordInput.getText())) {

          User inputUser = new User(id, nameInput.getText(), emailInput.getText(), passwordInput.getText());
          try {
            UserDAO.updateItem(inputUser);
            content.refreshTable();
          } catch (SQLException ex) {
            throw new RuntimeException(ex);
          }
          dispose();
        }
      }
    });
  }

  //Create user(no user required)
  public UserForm (TableModel content) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        if (passwordInput.getText() != null &&//validation
                confirmPasswordInput.getText() != null &&
                nameInput.getText() != null &&
                emailInput.getText() != null &&
                passwordInput.getText().equals(confirmPasswordInput.getText())) {
          int itemsCount = content.getRowCount() + 1;
          User inputUser = new User(itemsCount, nameInput.getText(), emailInput.getText(), passwordInput.getText());
          try {
            UserDAO.insertUserIfNotExists(inputUser);
            content.refreshTable();
          } catch (SQLException ex) {
            throw new RuntimeException(ex);
          }
          dispose();
        }
      }
    });
  }
}
