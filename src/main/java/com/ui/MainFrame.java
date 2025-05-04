package com.ui;

import com.dao.CustomersDAO;
import com.dao.UserDAO;
import com.model.Customer;
import com.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends JFrame {
  //ui
  private JButton logoutButton;
  private JButton viewInvoicesButton;
  private JButton viewUsersButton;
  private JButton viewCustomersButton;
  private JButton viewKatiAlloButton;
  private JCheckBox prediction1CheckBox;
  private JCheckBox prediction2CheckBox;
  private JCheckBox prediction3CheckBox;
  private JButton generateButton;
  private JPanel mainFramePanel;
  private JButton salvageDataFromOldButton;

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
    //invoices OnClick event listener
    viewInvoicesButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        //TASK make button show table
      }
    });
    //logout OnClick event listener
    logoutButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        //TASK make button open login frame and close current frame
      }
    });
    //OnClick calls python cleaner
    salvageDataFromOldButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        //TASK call the python script that calls the API
        //Hint look in /service
      }
    });
  }
}
