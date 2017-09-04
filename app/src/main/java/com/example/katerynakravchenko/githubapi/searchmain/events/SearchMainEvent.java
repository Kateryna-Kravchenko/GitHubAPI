package com.example.katerynakravchenko.githubapi.searchmain.events;


import com.example.katerynakravchenko.githubapi.entities.User;

import java.util.ArrayList;


public class SearchMainEvent {
    private String error;
    private ArrayList<User> user;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<User> getUser() {
        return user;
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }

}
