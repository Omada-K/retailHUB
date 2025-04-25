package com.model;

public class Discount {
  private int customerId;
  private double amount;
  private double discountPercentage;

  //Constructor
  public Discount (int customerId, double amount, double discountPercentage) {
    this.customerId = customerId;
    this.amount = amount;
    this.discountPercentage = discountPercentage;
  }

  //getters setters
  public int getCustomerId () { return customerId; }
  public void setCustomerId (int CustomerId) { this.customerId = CustomerId; }

  public double getAmount () { return amount; }
  public void setAmount (double Amount) { this.amount = Amount; }

  public double getDiscountPercentage () { return discountPercentage; }
  public void setDiscountPercentage (double DiscountPercentage) { this.discountPercentage = DiscountPercentage; }
}
