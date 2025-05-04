package com.ui;

import com.dao.ProductsDAO;
import com.model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProductForm extends BaseForm {
  private JPanel formPanel;
  private JButton saveButton;
  private JButton cancelButton;
  private JTextField nameInput;
  private JTextField descriptionInput;
  private JTextField priceInput;
  private JComboBox categotyBox;

  //Edit form(needs user)
  public ProductForm (TableModel content, Object productInput) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    Product product = (Product) productInput; //force generic object to be customer

    int id = product.getProductId();
    nameInput.setText(product.getProductName());
    descriptionInput.setText(product.getProductDescription());
    priceInput.setText(String.valueOf(product.getProductPrice()));

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        if (nameInput.getText() != null &&//validation
                descriptionInput.getText() != null &&
                priceInput.getText() != null
        ) {
          Product inputProduct = new Product(
                  id,
                  "test",
                  nameInput.getText(),
                  descriptionInput.getText(),
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
  public ProductForm (TableModel content) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        if (nameInput.getText() != null &&//validation
                descriptionInput.getText() != null &&
                priceInput.getText() != null
        ) {

          Product inputProduct = new Product(
                  "test",
                  nameInput.getText(),
                  descriptionInput.getText(),
                  Double.parseDouble(priceInput.getText())
          );
          try {
            ProductsDAO.insertProduct(inputProduct);
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
