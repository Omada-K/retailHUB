package com.service;

import com.dao.CustomersDAO;
import com.dao.ProductsDAO;
import com.dao.ProductCategoryDAO;
import com.model.Customer;
import com.model.Product;
import com.model.ProductCategory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class DataGenerator {

  public static void createDummyCustomers() {
    // ... unchanged ...
  }

  public static void createDummyProducts() {
    // Insert default categories if not already in DB
    try {
      // ProductCategory constructor expects (id, name), so give 0 as dummy id (DB auto-increments)
      ProductCategoryDAO.createItem(new ProductCategory(0, "Electronics"));
      ProductCategoryDAO.createItem(new ProductCategory(0, "Beauty"));
      ProductCategoryDAO.createItem(new ProductCategory(0, "Clothing"));
    } catch (SQLException ignored) {
      // ignore duplicate insert errors
    }

    String[] categories = {"Electronics", "Beauty", "Clothing"};
    String[] productNames = {"Headphones", "PC Keyboard", "Notebook", "T-shirt", "Backpack", "Mug", "Mouse"};

    Random rand = new Random();

    // Fetch existing product categories from DB
    ArrayList<ProductCategory> existingCategories = new ArrayList<>();
    try {
      existingCategories = ProductCategoryDAO.getData(); // static method
    } catch (SQLException e) {
      e.printStackTrace();
    }

    for (int i = 1; i < 16; i++) {
      String categoryName = categories[rand.nextInt(categories.length)];
      String name = productNames[rand.nextInt(productNames.length)] + " " + rand.nextInt(26);
      double price = (5 + rand.nextInt(96)) * 1.0;
      int quantity = 10 + rand.nextInt(1000);

      // Map categoryName to categoryId from existing DB list
      int categoryId = -1;
      for (ProductCategory cat : existingCategories) {
        if (cat.getCategoryName().equalsIgnoreCase(categoryName)) { // FIX: use getCategoryName()
          categoryId = cat.getCategoryId();
          break;
        }
      }

      // Only create product if categoryId is valid
      if (categoryId != -1) {
        // Assuming default productInOrder = 0
        Product product = new Product(categoryId, name, quantity, price, 0);
        try {
          ProductsDAO.createItem(product);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
