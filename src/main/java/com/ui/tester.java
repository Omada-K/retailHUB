package com.ui;

import java.util.Map;

public class tester {
    public static void main(String[] args){
        Map<String, String> credentials = Map.of(
                "admin", "pass123",
                "user1", "hello123",
                "alice", "qwerty"
                                                );
        String username_input = "admin"; //test this
        String password_input = "pass123"; //test this
        Auth_Maps creds = new Auth_Maps(username_input, password_input);
        if (creds.authentication_checker())
            System.out.println("pass");
        else
            System.out.println("nope");




   }

}
