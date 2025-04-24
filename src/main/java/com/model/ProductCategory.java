
package com.model;

public class ProductCategory {
  private int categoryId;
  private String description;
  private int quantity;
  private double priceAmount;

  public ProductCategory(int categoryId, String description, int quantity, double priceAmount) {
    this.categoryId = categoryId;
    this.description = description;
    this.quantity = quantity;
    this.priceAmount = priceAmount;
  }

  public int getCategoryId() { return categoryId; }
  public String getDescription() { return description; }
  public int getQuantity() { return quantity; }
  public double getPriceAmount() { return priceAmount; }
}
