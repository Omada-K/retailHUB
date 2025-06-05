package com.model;

/**
 * Model class for product_category table.
 */
public class ProductCategory {
  // Unique identifier for each category
  private int categoryId;

  // Name of the category
  private String categoryName;

  // Constructor
  //for inserting products WITHOUT id
  public ProductCategory(String categoryName) {
        this.categoryName = categoryName;
  }

  //for FETCHING products from db WITH id
  public ProductCategory(int categoryId, String categoryName) {
    this.categoryId = categoryId;
    this.categoryName = categoryName;
  }

  // Getters & Setters

  public int getCategoryId() { return categoryId; }
  public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
  public String getCategoryName() { return categoryName; }
  public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

  @Override
  public String toString() {
    return categoryName;
  }
}