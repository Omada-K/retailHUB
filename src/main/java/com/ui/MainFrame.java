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
import com.service.PythonRunner;
import com.ui.tablemodel.CustomerTableModel;
import com.ui.tablemodel.OrderTableModel;
import com.ui.tablemodel.ProductTableModel;
import com.ui.tablemodel.UserTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends BaseFrame {
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
  private JLabel welcomeLabel;

  public MainFrame (AppState state) {
    super();
    setContentPane(mainFramePanel);// don't forget this, the window will be empty
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    //set name of loggein user to the label
    Font currentFont = welcomeLabel.getFont();
    welcomeLabel.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 24));
    welcomeLabel.setText("Welcome to RetailHub " + state.loggedInUserName);

    //View customers OnClick event listener
    viewCustomersButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        try {
          List<Customer> customers = CustomersDAO.getData();
          CustomerTableModel model = new CustomerTableModel(customers);
          state.selectedTableType = AppState.TableTypes.Customers.toString();
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
          state.selectedTableType = AppState.TableTypes.Users.toString();
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
          List<Order> orders = OrdersDAO.getDataToDisplay();
          OrderTableModel model = new OrderTableModel(orders);
          state.selectedTableType = AppState.TableTypes.Orders.toString();
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
          state.selectedTableType = AppState.TableTypes.Products.toString();
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
          state.resetState();
          new LoginFrame(state);
        } catch (SQLException ex) {
          throw new RuntimeException(ex);
        }
        dispose();
      }
    });
    salvageDataFromOldButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        PythonRunner.runScript("get_api_data_script_main.py");
      }
    });
    //About frame
    aboutButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        new AboutFrame();
      }
    });
    //generate outputs(python)
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
    //python runner
    generateButton.addActionListener(new ActionListener() {
      // Note: When multiple checkboxes are selected, the JavaMain frame becomes unresponsive.
      // Each checkbox triggers a data visualization window, and the main frame remains frozen
      // until all these windows are manually closed. This blocking behavior is likely due to
      // the use of a GUI method (e.g., plt.show()) in Python, which halts Java execution
      // until the plot window is closed. Consider switching to non-blocking visualization or
      // saving plots to files instead.

      @Override
      public void actionPerformed (ActionEvent e) {
        if (prediction1CheckBox.isSelected()) {
          System.out.println("Clicked prediction1CheckBox");
          PythonRunner.runScript("checkbox1.py");
          PythonRunner.showImageFrame("data/boston1.png");
        }
        if (prediction2CheckBox.isSelected()) {
          System.out.println("Clicked prediction2CheckBox");
          PythonRunner.runScript("checkbox2.py");
          PythonRunner.showImageFrame("data/boston2.png");
        }
        if (prediction3CheckBox.isSelected()) {
          System.out.println("Clicked prediction3CheckBox");
          PythonRunner.runScript("checkbox3.py");
          PythonRunner.showImageFrame("data/boston3.png");
        }

      }
    });
  }
}
