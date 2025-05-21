package com.ui.tablemodel;

import com.dao.ProductsDAO;
import com.model.Product;

import javax.swing.*;
import java.util.List;

public class ProductTableModel extends TableModel<Product> {
  public ProductTableModel (List<Product> products) {
    super(products, new String[] {"ID", "Category", "Name", "Price", "Quantity"});
  }

  @Override
  public Object getValueAt (int rowIndex, int columnIndex) {
    Product product = data.get(rowIndex);

    if (columnIndex == 4) { // Quantity Column
      int amount = product.getAmountInStock();

      // Warning message if stock is low
      if (amount < 20) {
        JOptionPane.showMessageDialog(
                null,
                "Warning: Product \"" + product.getName() + "\" has low stock (" + amount + ").",
                "Low Stock Alert",
                JOptionPane.WARNING_MESSAGE);
      }
    }
    return switch (columnIndex) {
      case 0 -> product.getProductId();
      case 1 -> product.getCategory();
      case 2 -> product.getName();
      case 3 -> product.getItemPrice();
      case 4 -> product.getAmountInStock();
      default -> null;
    };
  }

  @Override
  public void refreshTable () {
    try {
      List<Product> updateData = ProductsDAO.getData();
      data.clear();
      data.addAll(updateData);
      fireTableDataChanged();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
