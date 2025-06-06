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

  // === Constructors ===

  // Full constructor with all fields (για selects/updates)
  public Product(int productId, int categoryId, String productName, int amountInStock, double productPrice) {
    this.productId = productId;
    this.categoryId = categoryId;
    this.productName = productName;
    this.amountInStock = amountInStock;
    this.productPrice = productPrice;
  }

  // Constructor χωρίς productId (για inserts με auto-increment)
  public Product(int categoryId, String productName, int amountInStock, double productPrice) {
    this.categoryId = categoryId;
    this.productName = productName;
    this.amountInStock = amountInStock;
    this.productPrice = productPrice;
  }

  // === Getters & Setters ===

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

  @Override
  public String toString() {
    return productName + " (" + amountInStock + " in stock, €" + productPrice + ")";
  }

  //**    return productInOrder;
  //  }
  //
  //  public int getAmountInStock () {

}
