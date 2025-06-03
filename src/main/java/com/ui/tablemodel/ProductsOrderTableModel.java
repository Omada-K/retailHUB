package com.ui.tablemodel;

import com.dao.ProductsDAO;
import com.dao.ProductCategoryDAO; // Themi edo allaxa auto: prostethike gia lookup onomatos kathgorias
import com.model.Product;
import java.sql.SQLException; // Themi edo allaxa auto: prostethike gia to try/catch
import java.util.List;

public class ProductsOrderTableModel extends TableModel<Product> {
  public ProductsOrderTableModel(List<Product> products) {
    super(products, new String[]{"Category", "Name", "Price", "In Order", "Item Cost", "In Stock"});
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Product product = data.get(rowIndex);
    return switch (columnIndex) {
      case 0 -> { // kanei lookup to category name
        try {
          yield ProductCategoryDAO.getCategoryNameById(product.getCategoryId());
        } catch (SQLException e) {
          e.printStackTrace();
          yield "Unknown";
        }
      }
      case 1 -> product.getProductName();
      case 2 -> product.getProductPrice();
      case 3 -> product.getProductInOrder();
      case 4 -> product.getProductInOrder() * product.getProductPrice(); // Themi edo allaxa kai ta dyo getters
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
