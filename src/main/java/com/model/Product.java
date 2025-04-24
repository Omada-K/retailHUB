package com.model;

public class Product {
  private int productId;
  private String productCategory;
  private String productName;
  private String productDescription;
  private double productPrice;

  //Constructor
  public Product (int productId, String productCategory, String productName, String productDescription, double productPrice) {
    this.productId = productId;
    this.productCategory = productCategory;
    this.productName = productName;
    this.productDescription = productDescription;
    this.productPrice = productPrice;
  }

  //Getters & Setters

  public int getProductId () { return productId; }

  public void setProductId (int productId) { this.productId = productId; }

  public String getProductCategory () { return productCategory; }

  public void setProductCategory (String productCategory) { this.productCategory = productCategory; }

  public String getProductName () { return productName; }

  public void setProductName (String productName) { this.productName = productName; }

  public String getProductDescription () { return productDescription; }

  public void setProductDescription (String productDescription) { this.productDescription = productDescription; }

  public double getProductPrice () { return productPrice; }

  public void setProductPrice (double productPrice) { this.productPrice = productPrice; }

}
