package com.ui;

import com.dao.CustomersDAO;
import com.dao.DiscountDAO;
import com.model.Customer;
import com.model.Discount;
import com.ui.tablemodel.TableModel;

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
  private JTextField discountTxtField;
  private JLabel labelBalance;

  //Edit form(needs user)
  public CustomerForm (TableModel content, Object customerInput) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    Customer customer = (Customer) customerInput; //force generic object to be customer

    int id = customer.getCustomerId();
    nameInput.setText(customer.getName());
    addressInput.setText(customer.getAddress());
    telephoneInput.setText(customer.getPhone());
    emailInput.setText(customer.getEmail());
    discountTxtField.setText(String.valueOf(customer.getDiscountPercentage()));
    labelBalance.setText(String.valueOf(customer.getCustomerBalance()));

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        if (nameInput.getText() != null &&//validation
                addressInput.getText() != null &&
                telephoneInput.getText() != null &&
                emailInput.getText() != null &&
                discountTxtField.getText() != null
        ) {

          Customer inputCustomer = new Customer(
                  id,
                  nameInput.getText(),
                  addressInput.getText(),
                  telephoneInput.getText(),
                  emailInput.getText()
          );
          Discount discount = new Discount(
                  id,
                  Double.parseDouble(labelBalance.getText()),
                  Float.parseFloat(discountTxtField.getText())
          );
          try {
            CustomersDAO.updateItem(inputCustomer);
            DiscountDAO.updateItem(discount);
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
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
          //          Discount discount = new Discount(
          //                  Float.parseFloat(discountTxtField.getText()),
          //                  Float.parseFloat(labelBalance.getText())
          //          );
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
