package com.model;

/**
 * This is the data model of the user.
 * It holds all the field relevant to the user of the retaiHUB app.
 * In python it would be a dictionary
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String userPassword;
    private boolean isAdmin;

    //No id constructor, for creating new users
    public User(String name, String email, String userPassword, boolean isAdmin) {
        this.name = name;
        this.email = email;
        this.userPassword = userPassword;
        this.isAdmin = isAdmin;
    }

    //constructor with id, for reading users
    public User(int id, String nameInput, String emailInput, String userPasswordInput, boolean isAdmin) {
        this.id = id;
        this.name = nameInput;
        this.email = emailInput;
        this.userPassword = userPasswordInput;
        this.isAdmin = isAdmin;
    }

    //getters setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
}
