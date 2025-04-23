package com.model;

public class Customer {
private int CustomerId;
  private String name;
  private String address;
  private String phone;
  private String email;
  private int UserId;

  //Constructor
  public Customer (int CustomerId, String name, String address, String phone, String email, int UserId) {
    this.CustomerId = CustomerId;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.UserId = UserId;
  }

  //Getters & Setters
  public int getCustomerId () { return CustomerId; }

  public void setCustomerId (int CustomerId) { this.CustomerId = CustomerId; }

  public String getName () { return name; }

  public void setName (String name) { this.name = name; }

  public String getAddress () { return address; }

  public void setAddress (String address) { this.address = address; }

  public String getPhone () { return phone; }

  public void setPhone (String phone) { this.phone = phone; }

  public String getEmail () { return email; }

  public void setEmail (String email) { this.email = email; }

  public int getUserId () { return UserId; }

  public void setUserId (int UserId) { this.UserId = UserId; }
}
