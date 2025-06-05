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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DataGenerator {


  public static java.sql.Date randomDateOfBirth() {
    LocalDate min = LocalDate.of(1975, 1, 1);
    LocalDate max = LocalDate.of(2007, 12, 30);

    long days = ChronoUnit.DAYS.between(min, max);
    long randomDays = (long) (Math.random() * days);

    LocalDate randomDate = min.plusDays(randomDays);
    return java.sql.Date.valueOf(randomDate);
  }

  public static void createDummyCustomers() {
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
      int points = rand.nextInt(21);
      java.sql.Date dateOfBirth = randomDateOfBirth();

      Customer customer = new Customer(name, address, phone, email, balance, points, dateOfBirth);
      try {
        CustomersDAO.createItem(customer);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void createDummyProducts() {
    // Insert default categories if not already in DB
    try {
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
