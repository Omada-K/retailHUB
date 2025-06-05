package com.model;

import java.sql.Date;

public class Customer {
  private int customerId;
  private String name;
  private String address;
  private String phone;
  private String email;
  private double balance;
  private int points;
  private Date dateOfBirth; // added

  // Full constructor (with customerId)
  public Customer(int customerId, String name, String address, String phone, String email, double balance, int points, Date dateOfBirth) {
    this.customerId = customerId;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.balance = balance;
    this.points = points;
    this.dateOfBirth = dateOfBirth;
  }

  // Constructor without customerId (e.g. for inserts)
  public Customer(String name, String address, String phone, String email, double balance, int points, Date dateOfBirth) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.balance = balance;
    this.points = points;
    this.dateOfBirth = dateOfBirth;
  }

  // Getters & Setters
  public int getCustomerId() { return customerId; }
  public void setCustomerId(int customerId) { this.customerId = customerId; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getAddress() { return address; }
  public void setAddress(String address) { this.address = address; }

  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public double getBalance() { return balance; }
  public void setBalance(double balance) { this.balance = balance; }

  public int getPoints() { return points; }
  public void setPoints(int points) { this.points = points; }

  public Date getDateOfBirth() { return dateOfBirth; }
  public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
}
