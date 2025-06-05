package com.ui;

import com.dao.OrdersDAO;
import com.dao.ProductsDAO;
import com.model.Customer;
import com.model.Order;
import com.model.Product;
import com.ui.tablemodel.OrderTableModel;
import com.ui.tablemodel.ProductsOrderTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderFrame extends BaseFrame {
  private JPanel formPanel;
  private JButton deleteButton;
  private JButton exitButton;
  private JTextField inputQuantity;
  private JComboBox comboProduct;
  private JTable productsTable;
  private JComboBox comboCustomer;
  private JButton addButton;
  private JButton editProductButton;

  // Edit form (requires an Order)
  public OrderFrame (
          Object OrderInput,
          ArrayList<Customer> availableCustomers,
          ArrayList<Product> availableProducts,
          AppState state
                    ) throws SQLException {
    super();
    setContentPane(formPanel); // set the main panel
    setupCancelButton(exitButton);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setSize(900, 680);

    Order order = (Order) OrderInput;
    ArrayList<Product> productsInOrder = ProductsDAO.getOrderedProducts(order.getOrderId());
    ProductsOrderTableModel contentModel = new ProductsOrderTableModel(productsInOrder);
    productsTable.setModel(contentModel);

    //setting the dropdown list
    DefaultComboBoxModel<Customer> customerModel = new DefaultComboBoxModel<>();
    for (Customer customer : availableCustomers) {
      customerModel.addElement(customer);
    }
    comboCustomer.setModel(customerModel);
    comboCustomer.setSelectedIndex(0);

    DefaultComboBoxModel<Product> productModel = new DefaultComboBoxModel<>();
    for (Product product : availableProducts) {
      productModel.addElement(product);
    }
    comboProduct.setModel(productModel);
    comboProduct.setSelectedIndex(0);

    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        if (!inputQuantity.getText().isEmpty()) {
          try {
            Product selectedProduct = (Product) comboProduct.getSelectedItem();
            int selectedProductId = selectedProduct.getProductId();
            int quantity = Integer.parseInt(inputQuantity.getText());
            //debug...
            System.out.println("Order id " + order.getOrderId());
            System.out.println("Product id " + selectedProductId);
            OrdersDAO.createOrderProduct(order.getOrderId(), selectedProductId, quantity);
          } catch (SQLException ex) {
            throw new RuntimeException(ex);
          }
        }
        contentModel.refreshTableWithOrder(order.getOrderId());

      }
    });

    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        int selectedProductId = productsTable.getSelectedRow();
        if (selectedProductId != -1) {
          int confirm = JOptionPane.showConfirmDialog(
                  OrderFrame.this,
                  "Are you sure you want to delete this row?",
                  "Confirm Delete",
                  JOptionPane.YES_NO_OPTION
                                                     );
          if (confirm == JOptionPane.YES_OPTION) {
            try {
              OrdersDAO.deleteOrderProduct(order.getOrderId(), selectedProductId);
            } catch (SQLException ex) {
              throw new RuntimeException(ex);
            }
          }

        } else {
          JOptionPane.showMessageDialog(null, "Please select a product first");
        }
      }
    });

    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        try {
          List<Order> orders = OrdersDAO.getData();
          OrderTableModel model = new OrderTableModel(orders);
          state.selectedTableType = AppState.TableTypes.Orders.toString();
          new TableFrame<>(state, model);
        } catch (SQLException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
  }

  // Create form (for new orders)
  //Create
  public OrderFrame (
          ArrayList<Customer> availableCustomers,
          ArrayList<Product> availableProducts,
          AppState state
                    ) throws SQLException {

    super();
    setContentPane(formPanel); // set the main panel
    setupCancelButton(exitButton);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setSize(900, 680);
    int createdOrderId = OrdersDAO.createItem(new Order(LocalDate.now(), LocalDate.now(), 0, 0));

    ArrayList<Product> productsInOrder = ProductsDAO.getOrderedProducts(createdOrderId);
    ProductsOrderTableModel contentModel = new ProductsOrderTableModel(productsInOrder);
    productsTable.setModel(contentModel);

    //setting the dropdown list
    DefaultComboBoxModel<Customer> customerModel = new DefaultComboBoxModel<>();
    for (Customer customer : availableCustomers) {
      customerModel.addElement(customer);
    }
    comboCustomer.setModel(customerModel);
    comboCustomer.setSelectedIndex(0);

    DefaultComboBoxModel<Product> productModel = new DefaultComboBoxModel<>();
    for (Product product : availableProducts) {
      productModel.addElement(product);
    }
    comboProduct.setModel(productModel);
    comboProduct.setSelectedIndex(0);

    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        if (!inputQuantity.getText().isEmpty()) {
          try {
            Product selectedProduct = (Product) comboProduct.getSelectedItem();
            int selectedProductId = selectedProduct.getProductId();
            int quantity = Integer.parseInt(inputQuantity.getText());
            //debug...
            System.out.println("Order id " + createdOrderId);
            System.out.println("Product id " + selectedProductId);
            OrdersDAO.createOrderProduct(createdOrderId, selectedProductId, quantity);
          } catch (SQLException ex) {
            throw new RuntimeException(ex);
          }
        }
        contentModel.refreshTableWithOrder(createdOrderId);

      }
    });

    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        int selectedProductId = productsTable.getSelectedRow();
        if (selectedProductId != -1) {
          int confirm = JOptionPane.showConfirmDialog(
                  OrderFrame.this,
                  "Are you sure you want to delete this row?",
                  "Confirm Delete",
                  JOptionPane.YES_NO_OPTION
                                                     );
          if (confirm == JOptionPane.YES_OPTION) {
            try {
              OrdersDAO.deleteOrderProduct(createdOrderId, selectedProductId);
            } catch (SQLException ex) {
              throw new RuntimeException(ex);
            }
          }

        } else {
          JOptionPane.showMessageDialog(null, "Please select a product first");
        }
      }
    });

    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        try {
          List<Order> orders = OrdersDAO.getData();
          OrderTableModel model = new OrderTableModel(orders);
          state.selectedTableType = AppState.TableTypes.Orders.toString();
          new TableFrame<>(state, model);
        } catch (SQLException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
  }
}
