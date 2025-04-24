package com.model;

public class Customer {
private int customerId;
  private String name;
  private String address;
  private String phone;
  private String email;
  private int userId;

  //Constructor
  public Customer (int customerId, String name, String address, String phone, String email, int userId) {
    this.customerId = customerId;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.userId = userId;
  }

  //Getters & Setters
  public int getCustomerId () { return customerId; }
  public void setCustomerId (int customerId) { this.customerId = customerId; }

  public String getName () { return name; }
  public void setName (String name) { this.name = name; }

  public String getAddress () { return address; }
  public void setAddress (String address) { this.address = address; }

  public String getPhone () { return phone; }
  public void setPhone (String phone) { this.phone = phone; }

  public String getEmail () { return email; }
  public void setEmail (String email) { this.email = email; }

  public int getUserId () { return userId; }
  public void setUserId (int userId) { this.userId = userId; }
}
