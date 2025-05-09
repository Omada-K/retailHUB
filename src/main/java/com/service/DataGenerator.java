//package com.service;
//
//import com.dao.CustomersDAO;
//import com.dao.ProductsDAO;
//import com.model.Customer;
//import com.model.Product;
//
//import javax.swing.*;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class DataGenerator {
//
//  public static void createDummyCustomers () {
//    CustomersDAO customersDAO = new CustomersDAO();
//    ArrayList<Customer> customers = new ArrayList<>();
//    Random rand = new Random();
//
//    String[] firstNames = {
//            "Nikos", "Giorgos", "Kostas", "Dimitris", "Giannis",
//            "Vasilis", "Andreas", "Marios", "Christos", "Panagiotis",
//            "Maria", "Eleni", "Anna", "Sofia", "Georgia",
//            "Katerina", "Vasiliki", "Stella", "Ioanna", "Despina"
//    };
//
//    String[] lastNames = {
//            "Papadopoulos", "Papanikolaou", "Georgiou", "Kostopoulos", "Nikolaidis",
//            "Vasileiou", "Lazaridis", "Spanos", "Theodorou", "Alexiou",
//            "Christou", "Panagiotou", "Makris", "Kotsis", "Tsoukalas",
//            "Giannakos", "Economou", "Charalampidis", "Savvidis", "Antoniou"
//    };
//
//    for (int i = 0; i < 50; i++) {
//      String firstName = firstNames[rand.nextInt(firstNames.length)];
//      String lastName = lastNames[rand.nextInt(lastNames.length)];
//      String phone = "69" + String.format("%08d", i);
//      String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + i + "@example.com";
//
//      //Customer customer = new Customer(firstName, lastName, phone, email);
//      customers.add(customer);
//    }
//
//    try {
//      for (Customer c : customers) {
//        CustomersDAO.createItem(c);
//      }
//    } catch (SQLException ex) {
//      JOptionPane.showMessageDialog(
//              null,
//              "Error inserting dummy customers:\n" + ex.getMessage(),
//              "Database Error",
//              JOptionPane.ERROR_MESSAGE);
//    }
//  }
//
//  public static void createDummyProducts () {
//    ProductsDAO productsDAO = new ProductsDAO();
//    Random rand = new Random();
//
//    String[] categories = {"Electronics", "Beauty", "Clothing", "Books", "Furniture", "Foods"};
//
//    // Electronics
//    ArrayList<String> electronicsDescriptions = new ArrayList<>(List.of(
//            "Wireless", "Compact", "Durable", "Fast", "Smart"
//                                                                       ));
//    ArrayList<Double> electronicsPrices = new ArrayList<>(List.of(
//            29.99, 49.99, 79.99, 99.99, 149.99
//                                                                 ));
//
//    // Beauty
//    ArrayList<String> beautyDescriptions = new ArrayList<>(List.of(
//            "Hydrating", "Natural", "Fragrant", "Soothing", "Gentle"
//                                                                  ));
//    ArrayList<Double> beautyPrices = new ArrayList<>(List.of(
//            9.99, 14.99, 19.99, 24.99, 29.99
//                                                            ));
//
//    // Clothing
//    ArrayList<String> clothingDescriptions = new ArrayList<>(List.of(
//            "Stylish", "Comfortable", "Trendy", "Slim", "Cozy"
//                                                                    ));
//    ArrayList<Double> clothingPrices = new ArrayList<>(List.of(
//            19.99, 24.99, 34.99, 44.99, 54.99
//                                                              ));
//
//    for (int i = 0; i < 50; i++) {
//      // Random category
//      String category = categories[rand.nextInt(categories.length)];
//
//      // Creates name
//      String baseName = switch (category) {
//        case "Electronics" -> "Device";
//        case "Beauty" -> "Cosmetic";
//        case "Clothing" -> "Outfit";
//        default -> "Product";
//      };
//      String productName = baseName + "_" + i; // όχι απαραίτητα unique στο DB, αλλά απλό
//
//      // Select appropriate description & price based on category
//      String description;
//      double price;
//
//      if (category.equals("Electronics")) {
//        description = electronicsDescriptions.get(rand.nextInt(electronicsDescriptions.size()));
//        price = electronicsPrices.get(rand.nextInt(electronicsPrices.size()));
//      } else if (category.equals("Beauty")) {
//        description = beautyDescriptions.get(rand.nextInt(beautyDescriptions.size()));
//        price = beautyPrices.get(rand.nextInt(beautyPrices.size()));
//      } else {
//        description = clothingDescriptions.get(rand.nextInt(clothingDescriptions.size()));
//        price = clothingPrices.get(rand.nextInt(clothingPrices.size()));
//      }
//
//      // create and insert product
//      Product product = new Product(category, productName, description, price, 100);
//
//      try {
//        ProductsDAO.insertItem(product);
//      } catch (SQLException ex) {
//        JOptionPane.showMessageDialog(
//                null,
//                "Error inserting dummy product:\n" + ex.getMessage(),
//                "Database Error",
//                JOptionPane.ERROR_MESSAGE
//                                     );
//      }
//    }
//  }
//}
//
/// /create dummy data
/// / create  methods for each model that creates dummy data
/// / and put it in the db using the DAO
/// /tip: in the end create a static 'mega' method that calls all the
/// /smaller methods -see createAllTables() by chris
/// /all done 5/5/25
