package com.model;

public class Discount {
    private int customerId;
    private double balance;
    private float discountPercentage;

    public Discount(int customerId, double balance, float discountPercentage) {
        this.customerId = customerId;
        this.balance = balance;
        this.discountPercentage = discountPercentage;
    }

    public Discount(double amount, float discountPercentage) {
        this.balance = balance;
        this.discountPercentage = discountPercentage;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getBalance() {
        return balance;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

}
