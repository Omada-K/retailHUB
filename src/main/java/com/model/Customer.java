package com.model;

public class Customer {
  private int customerId;
  private String name;
  private String address;
  private String phone;
  private String email;
  private double customerBalance;
  private float discountPercentage;

  //Constructors
  public Customer (int customerId, String name, String address, String phone, String email) {
    this.customerId = customerId;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
  }

  public Customer (String name, String address, String phone, String email) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
  }

  //Customer to display
  public Customer (
          int customerId, String name, String address, String phone, String email, double customerBalance,
          float discountPercentage
                  ) {
    this.customerId = customerId;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.customerBalance = customerBalance;
    this.discountPercentage = discountPercentage;

  }

  //Getters & Setters
  public int getCustomerId () { return customerId; }

  public void setCustomerId (int CustomerId) { this.customerId = CustomerId; }

  public String getName () { return name; }

  public void setName (String name) { this.name = name; }

  public String getAddress () { return address; }

  public void setAddress (String address) { this.address = address; }

  public String getPhone () { return phone; }

  public void setPhone (String phone) { this.phone = phone; }

  public String getEmail () { return email; }

  public void setEmail (String email) { this.email = email; }

  public double getCustomerBalance () {
    return customerBalance;
  }

  public float getDiscountPercentage () {
    return discountPercentage;
  }
}
