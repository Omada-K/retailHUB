package com.ui;

import com.dao.OrdersDAO;
import com.model.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderForm extends BaseForm {
  private JPanel formPanel;
  private JButton saveButton;
  private JButton cancelButton;
  private JTextField inputQuantity;
  private JComboBox comboProduct;
  private JComboBox comboCustomer;
  private JButton addProductButton;

  //Edit form(needs order)
  public OrderForm (TableModel content, Object OrderInput) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    Order order = (Order) OrderInput; //force generic object to be customer

    int id = order.getOrderId();
    inputQuantity.setText(String.valueOf(order.getAmount()));

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        if (inputQuantity.getText() != null //validation
        ) {
          Order inputOrder = new Order(
                  id,
                  LocalDate.now(),
                  Integer.parseInt(inputQuantity.getText()),
                  0,
                  0,
                  0
          );
          try {
            OrdersDAO.updateItem(inputOrder);
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
  public OrderForm (TableModel content) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        if (inputQuantity.getText() != null //validation
        ) {
          Order inputOrder = new Order(
                  LocalDate.now(),
                  Integer.parseInt(inputQuantity.getText()),
                  0,
                  0,
                  0
          );
          try {
            OrdersDAO.insertOrder(inputOrder);
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
