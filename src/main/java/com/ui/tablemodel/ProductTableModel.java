package com.ui.tablemodel;

import com.dao.ProductsDAO;
import com.model.Product;

import java.util.List;

public class ProductTableModel extends TableModel<Product> {
  public ProductTableModel (List<Product> products) {
    super(products, new String[] {"ID", "Category", "Name", "Price", "Quantity"});
  }

  @Override
  public Object getValueAt (int rowIndex, int columnIndex) {

    Product product = data.get(rowIndex);
    return switch (columnIndex) {
      case 0 -> product.getProductId();
      case 1 -> product.getCategoryId();
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
