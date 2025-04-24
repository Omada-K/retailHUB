package com.model;

public class Customers {
  private int id;
  private String name, address, phone, email;

  public Customers(int id, String name, String address, String phone, String email) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
  }

  // Getters και Setters
  public int getId() { return id; }
  public String getName() { return name; }
  public String getAddress() { return address; }
  public String getPhone() { return phone; }
  public String getEmail() { return email; }
}
