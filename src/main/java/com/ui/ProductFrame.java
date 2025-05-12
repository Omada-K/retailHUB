package com.ui;

import com.dao.ProductsDAO;
import com.model.Product;
import com.ui.tablemodel.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProductFrame extends BaseFrame {
  private JPanel formPanel;
  private JButton saveButton;
  private JButton cancelButton;
  private JTextField nameInput;
  private JTextField priceInput;
  private JTextField inputStock;
  private String[] categories = {"Electronics", "Beauty", "Clothing", "Books", "Furniture", "Foods"};
  private JComboBox categoryBox;

  //Edit form(needs user)
  public ProductFrame (TableModel content, Object productInput) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    Product product = (Product) productInput; //force generic object to be customer

    categoryBox.setModel(new DefaultComboBoxModel<>(categories));
    categoryBox.setSelectedItem(product.getCategory());

    int id = product.getProductId();
    nameInput.setText(product.getName());
    priceInput.setText(String.valueOf(product.getItemPrice()));
    inputStock.setText(String.valueOf(product.getAmountInStock()));

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        if (!nameInput.getText().isEmpty() &&//validation
                !inputStock.getText().isEmpty() &&
                !priceInput.getText().isEmpty()
        ) {
          Product inputProduct = new Product(
                  id,
                  (String) categoryBox.getSelectedItem(),
                  nameInput.getText(),
                  Integer.parseInt(inputStock.getText()),
                  Double.parseDouble(priceInput.getText())
          );
          try {
            ProductsDAO.updateItem(inputProduct);
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
  public ProductFrame (TableModel content) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    categoryBox.setModel(new DefaultComboBoxModel<>(categories));

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        if (!nameInput.getText().isEmpty() &&//validation
                !inputStock.getText().isEmpty() &&
                !priceInput.getText().isEmpty()
        ) {
          Product inputProduct = new Product(
                  (String) categoryBox.getSelectedItem(),
                  nameInput.getText(),
                  Integer.parseInt(inputStock.getText()),
                  Double.parseDouble(priceInput.getText())
          );
          try {
            ProductsDAO.createItem(inputProduct);
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
