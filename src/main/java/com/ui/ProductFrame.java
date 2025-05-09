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
  private JTextField descriptionInput;
  private JTextField priceInput;
  private JTextField inputStock;
  private String[] categories = {"Electronics", "Beauty", "Clothing", "Books", "Furniture", "Foods"};
  private JComboBox categotyBox;

  //Edit form(needs user)
  public ProductFrame (TableModel content, Object productInput) {
    super();
    setupCancelButton(cancelButton);
    setContentPane(formPanel);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    Product product = (Product) productInput; //force generic object to be customer

    categotyBox.setModel(new DefaultComboBoxModel<>(categories));
    categotyBox.setSelectedItem(product.getCategory());

    int id = product.getProductId();
    nameInput.setText(product.getName());
    priceInput.setText(String.valueOf(product.getItemPrice()));

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        if (nameInput.getText() != null &&//validation
                descriptionInput.getText() != null &&
                priceInput.getText() != null
        ) {
          Product inputProduct = new Product(
                  id,
                  (String) categotyBox.getSelectedItem(),
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

    categotyBox.setModel(new DefaultComboBoxModel<>(categories));

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {

        if (nameInput.getText() != null &&//validation
                descriptionInput.getText() != null &&
                priceInput.getText() != null
        ) {
          Product inputProduct = new Product(
                  (String) categotyBox.getSelectedItem(),
                  nameInput.getText(),
                  Integer.parseInt(inputStock.getText()),
                  Double.parseDouble(priceInput.getText())
          );
          try {
            ProductsDAO.insertItem(inputProduct);
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
