package com.ui;

import com.model.User;
import java.util.Map;

public class Auth_Maps {
    private String username;
    private String password;
    private User user1 = new User("admin", "admin@", "pass123");
    private String user1_email = user1.getEmail();
    private String user1_password = user1.getUserPassword();

    private Map<String, String> userCredentials = Map.of(
            user1_email, user1_password,
            "user1", "hello123",
            "alice", "qwerty"
                                            );



    public Auth_Maps (String username, String password)
    {
        this.username = username;
        this.password = password;

    }
    public boolean authentication_checker()
    {
        if (userCredentials.containsKey(username)) {
            String correctPassword = userCredentials.get(username);
            if (correctPassword.equals(password)) {
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




}}
