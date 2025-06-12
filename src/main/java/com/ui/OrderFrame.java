package com.ui;

import com.dao.OrdersDAO;
import com.dao.ProductsDAO;
import com.model.Customer;
import com.model.Order;
import com.model.Product;
import com.ui.tablemodel.OrderTableModel;
import com.ui.tablemodel.ProductsOrderTableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
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
          if (order.getProductCount() < 1) {
            OrdersDAO.deleteItem(order.getOrderId());
          }
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
    int tempPrice = 0;
    int tempQuantity = 0;
    Order orderInput = new Order(LocalDate.now(), LocalDate.now(), tempPrice, tempQuantity);
    int createdOrderId = OrdersDAO.createItem(orderInput);

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
          orderInput.setProductCount(orderInput.getProductCount() + Integer.parseInt(inputQuantity.getText()));
          try {
            Product selectedProduct = (Product) comboProduct.getSelectedItem();
            Customer selectedCustomer = (Customer) comboCustomer.getSelectedItem();
            int selectedCustomerId = selectedCustomer.getCustomerId();
            int selectedProductId = selectedProduct.getProductId();
            int quantity = Integer.parseInt(inputQuantity.getText());

            OrdersDAO.createOrderProduct(createdOrderId, selectedProductId, orderInput.getProductCount());
            OrdersDAO.createOrderCustomer(createdOrderId, selectedCustomerId);
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
          Order order = OrdersDAO.getOrderById(createdOrderId);
          if (order.getProductCount() < 1) {
            OrdersDAO.deleteItem(createdOrderId);
          }
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

  {
    // GUI initializer generated by IntelliJ IDEA GUI Designer
    // >>> IMPORTANT!! <<<
    // DO NOT EDIT OR ADD ANY CODE HERE!
    $$$setupUI$$$();
  }

  /**
   * Method generated by IntelliJ IDEA GUI Designer
   * >>> IMPORTANT!! <<<
   * DO NOT edit this method OR call it in your code!
   *
   * @noinspection ALL
   */
  private void $$$setupUI$$$ () {
    formPanel = new JPanel();
    formPanel.setLayout(new GridBagLayout());
    formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
    exitButton = new JButton();
    exitButton.setText("Exit");
    GridBagConstraints gbc;
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 7;
    gbc.anchor = GridBagConstraints.EAST;
    formPanel.add(exitButton, gbc);
    deleteButton = new JButton();
    deleteButton.setText("Delete product");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 7;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    formPanel.add(deleteButton, gbc);
    inputQuantity = new JTextField();
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    formPanel.add(inputQuantity, gbc);
    comboProduct = new JComboBox();
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 2;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    formPanel.add(comboProduct, gbc);
    final JLabel label1 = new JLabel();
    label1.setText("Customer");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    formPanel.add(label1, gbc);
    final JScrollPane scrollPane1 = new JScrollPane();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.gridwidth = 6;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(30, 30, 30, 30);
    formPanel.add(scrollPane1, gbc);
    productsTable = new JTable();
    scrollPane1.setViewportView(productsTable);
    comboCustomer = new JComboBox();
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    formPanel.add(comboCustomer, gbc);
    final JLabel label2 = new JLabel();
    label2.setText("Order ID");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    formPanel.add(label2, gbc);
    addButton = new JButton();
    addButton.setText("Add");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.EAST;
    formPanel.add(addButton, gbc);
    final JLabel label3 = new JLabel();
    label3.setText("Product");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.WEST;
    formPanel.add(label3, gbc);
    editProductButton = new JButton();
    editProductButton.setText("Edit Product");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 7;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    formPanel.add(editProductButton, gbc);
    final JLabel label4 = new JLabel();
    label4.setText("Order Value $$");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.WEST;
    formPanel.add(label4, gbc);
    final JLabel label5 = new JLabel();
    label5.setText("Quantity");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.WEST;
    formPanel.add(label5, gbc);
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$ () { return formPanel; }

}
