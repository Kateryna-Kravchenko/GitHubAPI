package com.example.katerynakravchenko.githubapi.detailsscreen.ui;

import com.example.katerynakravchenko.githubapi.api.DetailsUser;

/**
 * Created by katerynakravchenko on 18.07.17.
 */

public interface UserMainView {
    void showProgress();
    void hideProgress();
    void showUIElements();
    void hideUIElements();

    void setUsers(DetailsUser user);
    void onGetUsersError(String error);
}