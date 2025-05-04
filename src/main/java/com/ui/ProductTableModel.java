package com.ui;

import com.dao.ProductsDAO;
import com.model.Product;

import java.util.List;

public class ProductTableModel extends TableModel<Product> {
  public ProductTableModel (List<Product> products) {
    super(products, new String[] {"ID", "Category", "Name", "Description", "Price"});
  }

  @Override
  public Object getValueAt (int rowIndex, int columnIndex) {

    Product product = data.get(rowIndex);
    return switch (columnIndex) {
      case 0 -> product.getProductId();
      case 1 -> product.getProductCategory();
      case 2 -> product.getProductName();
      case 3 -> product.getProductDescription();
      case 4 -> product.getProductPrice();
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
