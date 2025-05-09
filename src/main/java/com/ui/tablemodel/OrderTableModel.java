package com.ui.tablemodel;

import com.dao.OrdersDAO;
import com.model.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderTableModel extends TableModel<Order> {
  public OrderTableModel (List<Order> orders) {
    super(orders, new String[] {"ID", "Created At", "Updated At", "Quantity", "Price", "Number of Product"});
  }

  @Override
  public Object getValueAt (int rowIndex, int columnIndex) {
    Order order = data.get(rowIndex);
    return switch (columnIndex) {
      case 0 -> order.getOrderId();
      case 1 -> order.getCreatedAt();
      case 2 -> order.getUpdateAt();
      case 3 -> order.getPrice();
      case 4 -> order.getProductCount();
      default -> null;
    };
  }

  @Override
  public void refreshTable () {
    try {
      List<Order> updatedData = OrdersDAO.getData();
      data.clear();
      data.addAll(updatedData);
      fireTableDataChanged();
    } catch (SQLException e) {
      throw new RuntimeException("Failed to refresh order table", e);
    }
  }
}
