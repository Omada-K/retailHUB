package com.ui;

import java.util.ArrayList;

public class Auth {
    private ArrayList<String> UsernameArray = new ArrayList();
    private ArrayList<String> PasswordArray = new ArrayList();
    private String username_input;
    private String password_input;

    public Auth(String username, String password, ArrayList usernames, ArrayList passwords) {
        username_input = username;
        password_input = password;
        UsernameArray = usernames;
        PasswordArray = passwords;

    }

    public String getUsername() {
        return username_input;
    }
    public String getPassword() {
        return password_input;
    }
//    public void printLists(){
//        for (Object item : UsernameArray)
//            System.out.println(item);
//        for (Object item : PasswordArray)
//            System.out.println(item);
//    }

    private boolean Array_looper(ArrayList list, String target) {
        for (Object user_item : list) {
            if (user_item.equals(target))
                return true;
        }
        return false;
    }

    public boolean Checker()
    {
        return Array_looper(UsernameArray, username_input) && Array_looper(PasswordArray, password_input);
    }
    public void authentication_message()
    {
        if (Checker())
            System.out.println("You can pass");
        else
            System.out.println("YOU SHALL NOT PASS");
    }


}
