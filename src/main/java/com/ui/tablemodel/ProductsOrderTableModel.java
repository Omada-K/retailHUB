package com.ui.tablemodel;

import com.dao.ProductsDAO;
import com.dao.ProductCategoryDAO;
import com.model.Product;
import com.model.ProductCategory; // Needed for category lookup
import java.sql.SQLException;
import java.util.List;

public class ProductsOrderTableModel extends TableModel<Product> {
  public ProductsOrderTableModel(List<Product> products) {
    super(products, new String[]{"Category", "Name", "Price", "In Order", "Item Cost", "In Stock"});
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Product product = data.get(rowIndex);
    return switch (columnIndex) {
      case 0 -> {
        // Lookup the category name using ONLY getData() and a loop
        String categoryName = "Unknown";
        try {
          for (ProductCategory c : ProductCategoryDAO.getData()) {
            if (c.getCategoryId() == product.getCategoryId()) {
              categoryName = c.getCategoryName();
              break;
            }
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        yield categoryName;
      }
      case 1 -> product.getProductName();
      case 2 -> product.getProductPrice();
      case 3 -> {
        // Replace this with your actual method or field that returns "quantity in order"
        // If it doesn't exist, use 0 or an empty string as a placeholder
        yield 0; // or product.getAmountInStock() if you want to show stock instead
      }
      case 4 -> {
        // Multiply the price by the "in order" quantity
        // Replace 0 with the same value as above for "in order"
        yield 0; // or product.getProductPrice() * product.getAmountInStock();
      }
      case 5 -> product.getAmountInStock();
      default -> null;
    };
  }

  @Override
  public void refreshTable() {
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
