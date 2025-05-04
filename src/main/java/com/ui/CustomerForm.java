package com.ui;

import com.dao.CustomersDAO;
import com.model.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CustomerForm extends BaseForm {
  private JPanel formPanel;
  private JButton saveButton;
  private JButton cancelButton;
  private JTextField nameInput;
  private JTextField telephoneInput;
  private JTextField addressInput;
  private JTextField emailInput;
  private JTextField textField5;

  //Edit form(needs user)
  public CustomerForm (TableModel content, Object customerInput) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    Customer customer = (Customer) customerInput; //force generic object to be customer

    int id = customer.getCustomerId();
    nameInput.setText(customer.getName());
    addressInput.setText(customer.getAddress());
    telephoneInput.setText(customer.getPhone());
    emailInput.setText(customer.getEmail());

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        if (nameInput.getText() != null &&//validation
                addressInput.getText() != null &&
                telephoneInput.getText() != null &&
                emailInput.getText() != null
        ) {

          Customer inputCustomer = new Customer(
                  id,
                  nameInput.getText(),
                  addressInput.getText(),
                  telephoneInput.getText(),
                  emailInput.getText()
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
  public CustomerForm (TableModel content) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        if (nameInput.getText() != null &&//validation
                addressInput.getText() != null &&
                telephoneInput.getText() != null &&
                emailInput.getText() != null
        ) {

          Customer inputCustomer = new Customer(
                  nameInput.getText(),
                  addressInput.getText(),
                  telephoneInput.getText(),
                  emailInput.getText()
          );
          try {
            CustomersDAO.insertCustomer(inputCustomer);
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
