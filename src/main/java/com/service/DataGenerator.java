package com.service;

import com.dao.CustomersDAO;
import com.dao.ProductsDAO;
import com.model.Customer;
import com.model.Product;

import java.sql.SQLException;
import java.util.Random;

public class DataGenerator {

    /**
     * Generates 15 random customers using Greek-like personal information.
     * Each customer includes: name, address, phone number, email, balance and discount.
     * The generated customer is inserted into the database using CustomersDAO.
     */
    public static void createDummyCustomers() {
        // Sample Greek male and female first names
        String[] maleNames = {"Nikos", "Giorgos", "Kostas", "Dimitris", "Giannis", "Vasilis", "Andreas"};
        String[] femaleNames = {"Maria", "Eleni", "Anna", "Sofia", "Georgia", "Katerina", "Vasiliki"};

        // Common Greek last names
        String[] lastNames = {"Papadopoulos", "Papanikolaou", "Georgiou", "Kostopoulos", "Nikolaidis"};

        // Cities and street names in Greece for address generation
        String[] cities = {"Athens", "Thessaloniki", "Patras", "Larisa", "Volos", "Heraklion", "Ioannina", "Kavala"};
        String[] streets = {"Solonos", "Venizelou", "Tsimiski", "Papafi", "Egnatia", "Kountouriotou", "Agiou Dimitriou"};

        Random rand = new Random();

        for (int i = 0; i < 15; i++) {
            // Randomly select first name and last name
            String firstName = rand.nextBoolean()
                    ? maleNames[rand.nextInt(maleNames.length)]
                    : femaleNames[rand.nextInt(femaleNames.length)];
            String lastName = lastNames[rand.nextInt(lastNames.length)];
            String name = firstName + " " + lastName;

            // Create a full address using street + number + city
            String address = streets[rand.nextInt(streets.length)] + " " + (1 + rand.nextInt(100)) + ", " +
                    cities[rand.nextInt(cities.length)];

            // Generate a Greek mobile phone number starting with 69
            String phone = "69" + (10000000 + rand.nextInt(89999999));

            // Construct a simple email based on the name
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@example.com";

            // Generate balance between €10 and €500, rounded to 2 decimal digits
            double balance = Math.round((10 + rand.nextDouble() * 490) * 100.0) / 100.0;

            // Random discount percentage between 0 and 20
            int discount = rand.nextInt(21);

            // Create Customer object and insert into the database
            Customer customer = new Customer(name, address, phone, email, balance, discount);
            try {
                CustomersDAO.createItem(customer);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Generates 15 random products with realistic names, categories, prices, and quantities.
     * Each product is inserted into the database using ProductsDAO.
     */
    public static void createDummyProducts() {
        String[] categories = {"Electronics", "Books", "Clothing", "Home", "Toys"};
        String[] productNames = {"Headphones", "PC Keyboard", "Notebook", "T-shirt", "Backpack", "Mug", "Mouse"};

        Random rand = new Random();

        for (int i = 0; i < 15; i++) {
            String category = categories[rand.nextInt(categories.length)];
            String name = productNames[rand.nextInt(productNames.length)] + " " + (char) ('A' + rand.nextInt(26));

            // Whole number price with 2 decimal places (e.g., 50.00)
            double price = (5 + rand.nextInt(96)) * 1.0;

            int quantity = 10 + rand.nextInt(1000);

            Product product = new Product(category, name, quantity, price);
            try {
                ProductsDAO.createItem(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}