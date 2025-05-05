package com.ui;

import com.dao.OrdersDAO;
import com.model.Order;
import com.ui.tablemodel.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderForm extends BaseForm {
  private JPanel formPanel;
  private JButton deleteButton;
  private JButton exitButton;
  private JTextField inputQuantity;
  private JComboBox comboProduct;
  private JTable productsTable;
  private JComboBox comboCustomer;
  private JButton addButton;

  //Edit form(needs order)
  public OrderForm (TableModel content, Object OrderInput) {
    super();
    setupCancelButton(exitButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    Order order = (Order) OrderInput; //force generic object to be customer

    int id = order.getOrderId();
    inputQuantity.setText(String.valueOf(order.getAmount()));

    deleteButton.addActionListener(new ActionListener() {
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
    setupCancelButton(exitButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    deleteButton.addActionListener(new ActionListener() {
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
