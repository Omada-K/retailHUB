package com.ui.tablemodel;

import com.dao.ProductsDAO;
import com.model.Product;
import com.dao.ProductCategoryDAO;
import com.model.ProductCategory;
import java.util.ArrayList;
import java.sql.SQLException;

import java.util.List;

public class ProductTableModel extends TableModel<Product> {
  public ProductTableModel (List<Product> products) {
    super(products, new String[] {"ID", "Category", "Name", "Price", "Quantity"});
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Product product = data.get(rowIndex);
    switch (columnIndex) {
      case 0: return product.getProductId();
      case 1:
        // Lookup category name by categoryId, catching SQLException since getCategoryNameById throws
        try {
          ArrayList<ProductCategory> categories = ProductCategoryDAO.getData();
          int searchId = product.getCategoryId();
          for (ProductCategory cat: categories) {
            if (cat.getCategoryId() == searchId) {
              return cat.getCategoryName();
            }
          }
          return "Unknown";
        } catch (SQLException e) {
          e.printStackTrace();
          return "Unknown";

        }
      case 2: return product.getProductName();
      case 3: return product.getProductPrice();
      case 4: return product.getAmountInStock();
      default: return null;
    }
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
