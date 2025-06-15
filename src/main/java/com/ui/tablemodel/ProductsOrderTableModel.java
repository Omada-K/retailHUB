package com.ui.tablemodel;

import com.dao.ProductsDAO;
import com.model.Product;

import java.util.List;

public class ProductsOrderTableModel extends TableModel<Product> {
    public ProductsOrderTableModel(List<Product> products) {
        super(products, new String[]{"Category", "Name", "Price", "In Order", "Item Cost", "In Stock"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Product product = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> product.getCategory();
            case 1 -> product.getName();
            case 2 -> product.getItemPrice();
            case 3 -> product.getItemsInOrder();
            case 4 -> product.getItemsInOrder() * product.getItemPrice();
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

    public void refreshTableWithOrder(int orderId) {
        try {
            List<Product> updateData = ProductsDAO.getOrderedProducts(orderId);
            data.clear();
            data.addAll(updateData);
            fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
