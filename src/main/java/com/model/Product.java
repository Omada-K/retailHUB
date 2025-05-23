package com.model;

import javax.swing.*;

public class Product {
  private int productId;
  private int categoryId;
  private String categoryName;
  private String productName;
  private int amountInStock;
  private double productPrice;
  private int productInOrder;

  //Constructor
  public Product(int productId, String categoryName, String productName, int amountInStock, double productPrice) {
    this.productId = productId;
    this.categoryName = categoryName;
    this.productName = productName;
    this.amountInStock = amountInStock;
    this.productPrice = productPrice;
  }

  public Product(String categoryName, String productName, double productPrice, int productsInOrder, int amountInStock) {
    this.categoryName = categoryName;
    this.productName = productName;
    this.productPrice = productPrice;
    this.productInOrder = productsInOrder;
    this.amountInStock = amountInStock;
  }
  public Product(int categoryId, String productName, int amountInStock, double productPrice) {
    this.categoryId = categoryId;
    this.productName = productName;
    this.amountInStock = amountInStock;
    this.productPrice = productPrice;
  }


  //Products when they are in an Order
  public Product (int categoryId, String productName, double productPrice, int productsInOrder, int amountInStock) {
    this.productId = productId;
    this.productName = productName;
    this.productPrice = productPrice;
    this.productInOrder = productsInOrder;
    this.amountInStock = amountInStock;
  }



  //Getters & Setters

  public int getProductId () { return productId; }

  public int getCategoryId () { return categoryId; }

  public String getCategoryName(){ return categoryName; }

  public String getName () { return productName; }

  public int getItemsInOrder () {
    return productInOrder;
  }

  public int getAmountInStock () {
    //TODO needs improvement
    if (amountInStock < 10) {
      new JOptionPane("add more?");
    }
    return amountInStock;
  }

  public double getItemPrice () { return productPrice; }

}
