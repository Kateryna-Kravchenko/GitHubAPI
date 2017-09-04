package com.example.katerynakravchenko.githubapi.searchmain.ui;

import com.example.katerynakravchenko.githubapi.entities.User;

import java.util.List;

/**
 * Created by katerynakravchenko on 18.07.17.
 */

public interface SearchMainView {
    void showProgress();

    void hideProgress();

    void showUIElements();

    void hideUIElements();

    void setUsers(List<User> users);

    void onGetUsersError(String error);
}