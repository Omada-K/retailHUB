package com.ui;

import java.util.ArrayList;

public class Auth_arrays {
  private ArrayList<String> usernames_list = new ArrayList<>();
  private ArrayList<String> password_list = new ArrayList<>();
  private String username_input;
  private String password_input;

  public Auth_arrays(String username_input, String password_input){
    this.username_input = username_input;
    this.password_input = password_input;
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
    if (usernames_list.contains(username_input)) {
      int index = usernames_list.indexOf(username_input);
      String correctPassword = password_list.get(index);

      if (password_input.equals(correctPassword)) {
        System.out.println("Access granted. Welcome, " + username_input + "!");
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
