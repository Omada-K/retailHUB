package com.ui;

import java.util.ArrayList;
import java.util.List;

public class tester {
    public static void main(String[] args){
        ArrayList<String> usernameList = new ArrayList<>(List.of("alekos", "banana", "cherry"));
        ArrayList<String> passwordList = new ArrayList<>(List.of("123","spark123","fff"));
        String username_input = "alekos"; //test this
        String password_input = "22"; //test this


        Auth creds = new Auth(username_input,password_input,usernameList,passwordList);
        String username = creds.getUsername();
        String password = creds.getPassword();
        System.out.println(username);
        System.out.println(password);
        System.out.println(creds.Checker());
        creds.authentication_message();

   }

}
