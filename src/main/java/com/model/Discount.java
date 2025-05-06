package com.model;

public class Discount {
  private int customerId;
  private double amount;
  private float discountPercentage;

  public Discount (int customerId, double amount, float discountPercentage) {
    this.customerId = customerId;
    this.amount = amount;
    this.discountPercentage = discountPercentage;
  }

  public Discount (double amount, float discountPercentage) {
    this.amount = amount;
    this.discountPercentage = discountPercentage;
  }

  public int getCustomerId () { return customerId; }

  public double getAmount () { return amount; }

  public double getDiscountPercentage () { return discountPercentage; }
}
