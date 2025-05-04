package com.model;

import java.time.LocalDate;

public class Order {
  private int orderId;
  private LocalDate orderDate;
  private int quantity;
  private double amount;
  private int customerId;
  private int productId;

  //Constructor
  public Order (int orderId, LocalDate date, int quantity, double amount, int customerId, int productId) {
    this.orderId = orderId;
    this.orderDate = date;
    this.quantity = quantity;
    this.amount = amount;
    this.customerId = customerId;
    this.productId = productId;
  }

  public Order (LocalDate date, int quantity, double amount, int customerId, int productId) {
    this.orderDate = date;
    this.quantity = quantity;
    this.amount = amount;
    this.customerId = customerId;
    this.productId = productId;
  }

  //Getters & Setters
  public int getOrderId () { return orderId; }

  public void setOrderId (int orderId) { this.orderId = orderId; }

  public LocalDate getOrderDate () { return orderDate; }

  public void setOrderDate (LocalDate orderDate) { this.orderDate = orderDate; }

  public int getQuantity () { return quantity; }

  public void setQuantity (int quantity) { this.quantity = quantity; }

  public double getAmount () { return amount; }

  public void setAmount (double amount) { this.amount = amount; }

  public int getCustomerId () { return customerId; }

  public void setCustomerId (int customerId) { this.customerId = customerId; }

  public int getProductId () { return productId; }

  public void setProductId (int productId) { this.productId = productId; }
}

