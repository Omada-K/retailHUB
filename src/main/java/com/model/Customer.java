package com.model;

public class Customer {
  private int customerId;
  private String name;
  private String address;
  private String phone;
  private String email;
  private double balance;
  private int points;

  //Constructors
  public Customer (int customerId, String name, String address, String phone, String email, double balance, int points) {
    this.customerId = customerId;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.balance = balance;
    this.points = points;
  }

  public Customer (String name, String address, String phone, String email, double balance, int points) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.balance = balance;
    this.points = points;
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

  public double getBalance () {
    return balance;
  }

  public int getPoints () {
    return points;
  }
}
