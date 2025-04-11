package com.ui;

import java.util.HashMap;
import java.util.Scanner;

public class test2 {public static void main (String[] args) {
  Auth_arrays creds = new Auth_arrays("admin","123");
  creds.auth_add_user_in_list("admin","123");
  creds.auth_add_user_in_list("pa","ff");
  System.out.println(creds.get_username_list());
  System.out.println(creds.getPassword());
  creds.auth_check();



}}
