package com.ui;

import com.dao.ProductsDAO;
import com.dao.ProductCategoryDAO;
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

  // Edit form (needs user)
  public ProductFrame(TableModel content, Object productInput) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    Product product = (Product) productInput;

    categoryBox.setModel(new DefaultComboBoxModel<>(categories));
    // Set selected category name in combo box (lookup name from categoryId)
    String categoryName = ""; // CHANGED: Find name from id
    try {
      categoryName = ProductCategoryDAO.getCategoryNameById(product.getCategoryId());
    } catch (Exception ignored) {}
    categoryBox.setSelectedItem(categoryName);

    int id = product.getProductId();
    nameInput.setText(product.getProductName());
    priceInput.setText(String.valueOf(product.getProductPrice()));
    inputStock.setText(String.valueOf(product.getAmountInStock()));

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!nameInput.getText().isEmpty() &&
                !inputStock.getText().isEmpty() &&
                !priceInput.getText().isEmpty()) {

          // Get selected category name from combo box, then lookup id
          String selectedCategoryName = (String) categoryBox.getSelectedItem();
          int categoryId = -1; // CHANGED: Declare and init to invalid id
          try {
            categoryId = ProductCategoryDAO.getCategoryIdByName(selectedCategoryName); // CHANGED: Catch SQLException
          } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(ProductFrame.this, "Could not find category in database!", "Error", JOptionPane.ERROR_MESSAGE); // CHANGED: Optional user message
            return;
          }

          Product inputProduct = new Product(
                  id,
                  categoryId,
                  nameInput.getText(),
                  Integer.parseInt(inputStock.getText()),
                  Double.parseDouble(priceInput.getText()),
                  0 // default value for productInOrder
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

  // Create form (needs user)
  public ProductFrame(TableModel content) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    categoryBox.setModel(new DefaultComboBoxModel<>(categories));

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!nameInput.getText().isEmpty() &&
                !inputStock.getText().isEmpty() &&
                !priceInput.getText().isEmpty()) {

          // Get selected category name from combo box, then lookup id
          String selectedCategoryName = (String) categoryBox.getSelectedItem();
          int categoryId = -1; // CHANGED: Declare and init to invalid id
          try {
            categoryId = ProductCategoryDAO.getCategoryIdByName(selectedCategoryName); // CHANGED: Catch SQLException
          } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(ProductFrame.this, "Could not find category in database!", "Error", JOptionPane.ERROR_MESSAGE); // CHANGED: Optional user message
            return;
          }

          Product inputProduct = new Product(
                  categoryId,
                  nameInput.getText(),
                  Integer.parseInt(inputStock.getText()),
                  Double.parseDouble(priceInput.getText()),
                  0 // default value for productInOrder
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
