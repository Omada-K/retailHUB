package com.model;

public class Product {
  // Unique identifier for each product
  private int productId;

  // Foreign key: references ProductCategory
  private int categoryId;

  // Product name
  private String productName;

  // Amount of product in stock
  private int amountInStock;

  // Price per product
  private double productPrice;

  // Number of products included in an order
  private int productInOrder;

  // Full constructor with all fields
  public Product(int productId, int categoryId, String productName, int amountInStock, double productPrice, int productInOrder) {
    this.productId = productId;
    this.categoryId = categoryId;
    this.productName = productName;
    this.amountInStock = amountInStock;
    this.productPrice = productPrice;
    this.productInOrder = productInOrder;
  }

  // Constructor without productId (e.g., for inserts where id is auto-generated)
  public Product(int categoryId, String productName, int amountInStock, double productPrice, int productInOrder) {
    this.categoryId = categoryId;
    this.productName = productName;
    this.amountInStock = amountInStock;
    this.productPrice = productPrice;
    this.productInOrder = productInOrder;
  }

  // Getters & Setters

  public int getProductId() { return productId; }
  public void setProductId(int productId) { this.productId = productId; }

  public int getCategoryId() { return categoryId; }
  public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

  public String getProductName() { return productName; }
  public void setProductName(String productName) { this.productName = productName; }

  public int getAmountInStock() { return amountInStock; }
  public void setAmountInStock(int amountInStock) { this.amountInStock = amountInStock; }

  public double getProductPrice() { return productPrice; }
  public void setProductPrice(double productPrice) { this.productPrice = productPrice; }

  public int getProductInOrder() { return productInOrder; }
  public void setProductInOrder(int productInOrder) { this.productInOrder = productInOrder; }
}
