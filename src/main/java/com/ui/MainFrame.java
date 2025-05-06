package com.ui;

import com.dao.CustomersDAO;
import com.dao.OrdersDAO;
import com.dao.ProductsDAO;
import com.dao.UserDAO;
import com.model.Customer;
import com.model.Order;
import com.model.Product;
import com.model.User;
import com.service.DataGenerator;
import com.ui.tablemodel.CustomerTableModel;
import com.ui.tablemodel.OrderTableModel;
import com.ui.tablemodel.ProductTableModel;
import com.ui.tablemodel.UserTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends JFrame {
  //ui
  private JButton logoutButton;
  private JButton viewOrdersButton;
  private JButton viewUsersButton;
  private JButton viewCustomersButton;
  private JButton viewProductsButton;
  private JCheckBox prediction1CheckBox;
  private JCheckBox prediction2CheckBox;
  private JCheckBox prediction3CheckBox;
  private JButton generateButton;
  private JPanel mainFramePanel;
  private JButton salvageDataFromOldButton;
  private JButton aboutButton;
  private JButton generateSyntheticDataButton;

  public MainFrame (AppState state) {
    setContentPane(mainFramePanel);// don't forget this, the window will be empty
    setVisible(false);
    setResizable(true);
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    //View customers OnClick event listener
    viewCustomersButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        try {
          List<Customer> customers = CustomersDAO.getData();
          CustomerTableModel model = new CustomerTableModel(customers);
          new TableFrame<>(state, model);
        } catch (SQLException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
    //view users OnClick event listener
    viewUsersButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        try {
          List<User> users = UserDAO.getData();
          UserTableModel model = new UserTableModel(users);
          new TableFrame<>(state, model);
        } catch (SQLException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
    //orders OnClick event listener
    viewOrdersButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        try {
          List<Order> orders = OrdersDAO.getData();
          OrderTableModel model = new OrderTableModel(orders);
          new TableFrame<>(state, model);
        } catch (SQLException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
    //View Products here
    viewProductsButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        try {
          List<Product> products = ProductsDAO.getData();
          ProductTableModel model = new ProductTableModel(products);
          new TableFrame<>(state, model);
        } catch (SQLException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
    //logout OnClick event listener
    logoutButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        try {
          new LoginFrame(state);
        } catch (SQLException ex) {
          throw new RuntimeException(ex);
        }
        dispose();
      }
    });
    //OnClick calls python cleaner
    salvageDataFromOldButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        //Hint look in /service
      }
    });
    aboutButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        System.out.println("about");
        new AboutForm();
      }
    });
    generateSyntheticDataButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        // Insert 50 randomly generated customers
        DataGenerator.createDummyCustomers();
        System.out.println("50 random customers inserted successfully!");
        DataGenerator.createDummyProducts();
        System.out.println("Random Products Created");
      }
    });
  }
}
