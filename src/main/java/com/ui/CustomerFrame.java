package com.ui;

import com.dao.CustomersDAO;
import com.model.Customer;
import com.ui.tablemodel.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CustomerFrame extends BaseFrame {
  private JPanel formPanel;
  private JButton saveButton;
  private JButton cancelButton;
  private JTextField nameInput;
  private JTextField telephoneInput;
  private JTextField addressInput;
  private JTextField emailInput;
  private JTextField pointsInput;
  private JTextField balanceInput;
  private JTextField dateOfBirthInput; // added @Themi des to UI na to emfanisei

  //Edit form(needs user)
  public CustomerFrame (TableModel content, Object customerInput) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    Customer customer = (Customer) customerInput;

    int id = customer.getCustomerId();
    nameInput.setText(customer.getName());
    addressInput.setText(customer.getAddress());
    telephoneInput.setText(customer.getPhone());
    emailInput.setText(customer.getEmail());
    pointsInput.setText(String.valueOf(customer.getPoints()));
    balanceInput.setText(String.valueOf(customer.getBalance()));
    dateOfBirthInput.setText(customer.getDateOfBirth() != null ? customer.getDateOfBirth().toString() : ""); // ADDED

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        if (nameInput.getText() != null &&
                addressInput.getText() != null &&
                telephoneInput.getText() != null &&
                emailInput.getText() != null &&
                pointsInput.getText() != null) {

          java.sql.Date dateOfBirth = null; // ADDED
          try { // ADDED
            dateOfBirth = java.sql.Date.valueOf(dateOfBirthInput.getText());
          } catch (Exception ex) {
            // Optional: Show error to user
          }

          Customer inputCustomer = new Customer(
                  id,
                  nameInput.getText(),
                  addressInput.getText(),
                  telephoneInput.getText(),
                  emailInput.getText(),
                  Double.parseDouble(balanceInput.getText()),
                  Integer.parseInt(pointsInput.getText()),
                  dateOfBirth // ADDED
          );

          try {
            CustomersDAO.updateItem(inputCustomer);
            content.refreshTable();
          } catch (SQLException ex) {
            throw new RuntimeException(ex);
          }
          dispose();
        }
      }
    });
  }

  //Create form(needs user)
  public CustomerFrame (TableModel content) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        if (nameInput.getText() != null &&
                addressInput.getText() != null &&
                telephoneInput.getText() != null &&
                emailInput.getText() != null) {

          java.sql.Date dateOfBirth = null; // ADDED
          try { // ADDED
            dateOfBirth = java.sql.Date.valueOf(dateOfBirthInput.getText());
          } catch (Exception ex) {
            // Optional: Show error to user
          }

          Customer inputCustomer = new Customer(
                  nameInput.getText(),
                  addressInput.getText(),
                  telephoneInput.getText(),
                  emailInput.getText(),
                  Double.parseDouble(balanceInput.getText()),
                  Integer.parseInt(pointsInput.getText()),
                  dateOfBirth // ADDED
          );
          try {
            CustomersDAO.createItem(inputCustomer);
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
