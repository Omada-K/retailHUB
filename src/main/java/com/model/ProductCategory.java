package com.model;

public class ProductCategory {
  private int categoryId;
  private String description;
  private int quantity;
  private int priceAmount;

  //Constructor
  public ProductCategory (int categoryId, String description, int quantity, int priceAmount) {
    this.categoryId = categoryId;
    this.description = description;
    this.quantity = quantity;
    this.priceAmount = priceAmount;
  }

  //Getters & Setters
  public int getCategoryId () { return categoryId; }

  public void setCategoryId (int categoryId) { this.categoryId = categoryId; }

  public String getDescription () { return description; }

  public void setDescription (String description) { this.description = description; }

  public int getQuantity () { return quantity; }

  public void setQuantity (int quantity) { this.quantity = quantity; }

  public int getPriceAmount () { return priceAmount; }

  public void setPriceAmount (int priceAmount) { this.priceAmount = priceAmount; }
}
