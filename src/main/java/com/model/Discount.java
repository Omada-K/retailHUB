package com.model;

public class Discount {
  private int orderId;
  private double amount;

  //Constructor
  public Discount (int orderId, double amount) {
    this.orderId = orderId;
    this.amount = amount;
  }

  //Getters & Setters
  public int getOrderId () { return orderId; }
  public void setOrderId (int orderId) { this.orderId = orderId; }

  public double getAmount () { return amount; }
  public void setAmount (double amount) { this.amount = amount; }
}
