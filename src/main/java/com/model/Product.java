package com.model;

import javax.swing.*;

public class Product {
  private int productId;
  private String productCategory;
  private String productName;
  private int amountInStock;
  private double productPrice;
  private int productInOrder;

  //Constructor
  public Product (int productId, String category, String productName, int amountInStock, double productPrice) {
    this.productId = productId;
    this.productCategory = category;
    this.productName = productName;
    this.amountInStock = amountInStock;
    this.productPrice = productPrice;
  }

  public Product (String category, String productName, int amountInStock, double productPrice) {
    this.productCategory = category;
    this.productName = productName;
    this.amountInStock = amountInStock;
    this.productPrice = productPrice;
  }

  //Products when they are in an Order
  public Product (String category, String productName, double productPrice, int productsInOrder, int amountInStock) {
    this.productCategory = category;
    this.productName = productName;
    this.productPrice = productPrice;
    this.productInOrder = productsInOrder;
    this.amountInStock = amountInStock;
  }

  //Getters & Setters

  public int getProductId () { return productId; }

  public String getCategory () { return productCategory; }

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
