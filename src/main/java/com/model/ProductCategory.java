package com.model;

public class ProductCategory {
  private int categoryId;
  private String category;


  public ProductCategory (int categoryId, String category){
    this.categoryId = categoryId;
    this.category = category;

  }

  public int getCategoryId () {
    return categoryId;
  }

  public String getCategory () {
    return category;
  }

  public ProductCategory (String category){
    this.category = category;
  }
}