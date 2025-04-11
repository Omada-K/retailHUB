package com.ui;

import java.util.ArrayList;

public class Auth_arrays {
  private ArrayList<String> usernames_list = new ArrayList<>();
  private ArrayList<String> password_list = new ArrayList<>();
  private String username;
  private String password;

  public Auth_arrays(String username, String password){
    this.username = username;
    this.password = password;
  }
  public void auth_add_user_in_list(String username, String password)
  {
    usernames_list.add(username);
    password_list.add(password);
  }
  public String get_username_list(){
    return usernames_list.toString();
  }
  public String getPassword(){
    return password_list.toString();
  }
  public boolean auth_check(){
    if (usernames_list.contains(username)) {
      int index = usernames_list.indexOf(username);
      String correctPassword = password_list.get(index);

      if (password.equals(correctPassword)) {
        System.out.println("Access granted. Welcome, " + username + "!");
        return true;
      } else {
        System.out.println("Access denied. Incorrect password.");
        return false;
      }
    } else {
      System.out.println("Access denied. User not found.");
      return false;
    }

  }



}
