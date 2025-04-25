
package com.model;

public class Products {
  private int productId;
  private int productCategory;
  private String name;
  private String description;
  private double price;

  public Products(int productId, int productCategory, String name, String description, double price) {
    this.productId = productId;
    this.productCategory = productCategory;
    this.name = name;
    this.description = description;
    this.price = price;
  }

  public int getProductId() { return productId; }
  public int getProductCategory() { return productCategory; }
  public String getName() { return name; }
  public String getDescription() { return description; }
  public double getPrice() { return price; }
}
