
package com.model;

import java.sql.Date;

public class Orders {
  private int orderId;
  private Date date;
  private int quantity;
  private double amount;
  private int customerId;
  private int productId;

  public Orders(int orderId, Date date, int quantity, double amount, int customerId, int productId) {
    this.orderId = orderId;
    this.date = date;
    this.quantity = quantity;
    this.amount = amount;
    this.customerId = customerId;
    this.productId = productId;
  }

  public int getOrderId() { return orderId; }
  public Date getDate() { return date; }
  public int getQuantity() { return quantity; }
  public double getAmount() { return amount; }
  public int getCustomerId() { return customerId; }
  public int getProductId() { return productId; }
}
