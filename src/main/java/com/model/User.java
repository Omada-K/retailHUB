package com.model;

public class User {
  private int id;
  private String name;
  private String email;
  private String userPassword;

  //No id constructor, for creating new users
  public User (String name, String email, String userPassword) {
    this.name = name;
    this.email = email;
    this.userPassword = userPassword;
  }

  //constructor with id, for reading users
  public User (int id, String nameInput, String emailInput, String userPasswordInput) {
    this.id = id;
    this.name = nameInput;
    this.email = emailInput;
    this.userPassword = userPasswordInput;
  }

  //getters setters
  public int getId () {
    return id;
  }

  public String getName () {
    return name;
  }

  public String getEmail () {
    return email;
  }

  public String getUserPassword () {
    return userPassword;
  }
}
