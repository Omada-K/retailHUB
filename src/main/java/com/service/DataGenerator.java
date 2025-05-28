package com.service;

import com.dao.CustomersDAO;
import com.dao.ProductsDAO;
import com.dao.ProductCategoryDAO; // added for category lookup
import com.model.Customer;
import com.model.Product;
import com.model.ProductCategory; // added for category lookup

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class DataGenerator {

  public static void createDummyCustomers () {
    String[] maleNames = {"Nikos", "Giorgos", "Kostas", "Dimitris", "Giannis", "Vasilis", "Andreas"};
    String[] femaleNames = {"Maria", "Eleni", "Anna", "Sofia", "Georgia", "Katerina", "Vasiliki"};
    String[] lastNames = {"Papadopoulos", "Papanikolaou", "Georgiou", "Kostopoulos", "Nikolaidis"};
    String[] cities = {"Athens", "Thessaloniki", "Patras", "Larisa", "Volos", "Heraklion", "Ioannina", "Kavala"};
    String[] streets = {"Solonos", "Venizelou", "Tsimiski", "Papafi", "Egnatia", "Kountouriotou", "Agiou Dimitriou"};

    Random rand = new Random();

    for (int i = 0; i < 15; i++) {
      String firstName = rand.nextBoolean()
              ? maleNames[rand.nextInt(maleNames.length)]
              : femaleNames[rand.nextInt(femaleNames.length)];
      String lastName = lastNames[rand.nextInt(lastNames.length)];
      String name = firstName + " " + lastName;
      String address = streets[rand.nextInt(streets.length)] + " " + ( 1 + rand.nextInt(100) ) + ", " +
              cities[rand.nextInt(cities.length)];
      String phone = "69" + ( 10000000 + rand.nextInt(89999999) );
      String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@example.com";
      double balance = Math.round(( 10 + rand.nextDouble() * 490 ) * 100.0) / 100.0;
      int discount = rand.nextInt(21);

      Customer customer = new Customer(name, address, phone, email, balance, discount);
      try {
        CustomersDAO.createItem(customer);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void createDummyProducts () {
    // insert default categories if not already in DB
    try {
      ProductCategoryDAO.createItem(new ProductCategory("Electronics")); // insert if not exists
      ProductCategoryDAO.createItem(new ProductCategory("Beauty"));      // insert if not exists
      ProductCategoryDAO.createItem(new ProductCategory("Clothing"));    // insert if not exists
    } catch (SQLException ignored) {
      // ignore duplicate insert errors
    }

    String[] categories = {"Electronics", "Beauty", "Clothing"}; // used for mapping to category_id
    String[] productNames = {"Headphones", "PC Keyboard", "Notebook", "T-shirt", "Backpack", "Mug", "Mouse"};

    Random rand = new Random();

    // fetch existing product categories from DB
    ArrayList<ProductCategory> existingCategories = new ArrayList<>();
    try {
      existingCategories = new ProductCategoryDAO().getData(); // fetch from DB
    } catch (SQLException e) {
      e.printStackTrace();
    }

    for (int i = 1; i < 16; i++) {
      String categoryName = categories[rand.nextInt(categories.length)];
      String name = productNames[rand.nextInt(productNames.length)] + " " + rand.nextInt(26);
      double price = ( 5 + rand.nextInt(96) ) * 1.0;
      int quantity = 10 + rand.nextInt(1000);

      // map categoryName to categoryId from existing DB list
      int categoryId = -1;
      for (ProductCategory cat : existingCategories) {
        if (cat.getCategory().equalsIgnoreCase(categoryName)) {
          categoryId = cat.getCategoryId();
          break;
        }
      }

      // only create product if categoryId is valid
      if (categoryId != -1) {
        Product product = new Product(categoryId, name, quantity, price); // use constructor with categoryId
        try {
          ProductsDAO.createItem(product);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
