package com.example.katerynakravchenko.githubapi.detailsscreen.events;


import com.example.katerynakravchenko.githubapi.api.DetailsUser;

/**
 * Created by katerynakravchenko on 18.07.17.
 */

public class UserMainEvent {
    private String error;
    private DetailsUser user;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public DetailsUser getUser() {
        return user;
    }

    public void setUser(DetailsUser user) {
        this.user = user;
    }
}
