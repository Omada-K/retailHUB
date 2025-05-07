package com.ui;

import com.dao.UserDAO;
import com.model.User;
import com.ui.tablemodel.TableModel;

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
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    User user = (User) userInput; //force generic object to be User

    int id = user.getId();
    nameInput.setText(user.getName());
    emailInput.setText(user.getEmail());
    passwordInput.setText(user.getUserPassword());
    confirmPasswordInput.setText(user.getUserPassword());
    isManagerCheckBox.setSelected(user.getIsAdmin());

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        if (passwordInput.getText() != null &&//validation
                confirmPasswordInput.getText() != null &&
                nameInput.getText() != null &&
                emailInput.getText() != null &&
                passwordInput.getText().equals(confirmPasswordInput.getText())) {

          User inputUser = new User(
                  id, nameInput.getText(),
                  emailInput.getText(),
                  passwordInput.getText(),
                  isManagerCheckBox.isSelected());
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
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        if (passwordInput.getText() != null &&//validation
                confirmPasswordInput.getText() != null &&
                nameInput.getText() != null &&
                emailInput.getText() != null &&
                passwordInput.getText().equals(confirmPasswordInput.getText())) {
          int itemsCount = content.getRowCount() + 1;
          User inputUser = new User(
                  itemsCount, nameInput.getText(),
                  emailInput.getText(),
                  passwordInput.getText(),
                  isManagerCheckBox.isSelected());
          try {
            UserDAO.insertUser(inputUser);
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
