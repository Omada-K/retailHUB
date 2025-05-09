package com.model;

import java.time.LocalDate;

public class Order {
  private int orderId;
  private LocalDate createdAt;
  private LocalDate updatedAt;
  private double price;
  private int productCount;

  //Constructor
  public Order (int orderId, LocalDate createdAt, LocalDate updatedAt, double price, int productCount) {
    this.orderId = orderId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.price = price;
    this.productCount = productCount;
  }

  public Order (LocalDate createdAt, LocalDate updatedAt, double price, int productCount) {
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.price = price;
    this.productCount = productCount;
  }

  //Getters & Setters
  public int getOrderId () { return orderId; }

  public LocalDate getCreatedAt () { return createdAt; }

  public LocalDate getUpdateAt () { return updatedAt; }

  public double getPrice () { return price; }

  public int getProductCount () { return productCount; }

}

