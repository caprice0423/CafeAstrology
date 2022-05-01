package com.hfad.cafeastrology;

public class User {
    String username;

    public User(){
        username = DatabaseHelper.getName();
    }

    public String getUsername(){
        return username;
    }


}
