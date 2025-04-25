
package com.model;

public class ProductCategory {
  private int categoryId;
  private String description;
  private int quantity;

    this.categoryId = categoryId;
    this.description = description;
    this.quantity = quantity;
    this.priceAmount = priceAmount;
  }

  public int getCategoryId () { return categoryId; }
  public String getDescription () { return description; }
  public int getQuantity () { return quantity; }
}
