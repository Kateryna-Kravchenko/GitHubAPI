package com.example.katerynakravchenko.githubapi.detailsscreen;

import com.example.katerynakravchenko.githubapi.detailsscreen.events.UserMainEvent;
import com.example.katerynakravchenko.githubapi.detailsscreen.ui.UserMainView;


public interface UserMainPresenter {
    void onCreate();

    void onDestroy();

    void getUserDetails(String userURL);

    void UsersError(String error);


    void onEventMainThread(UserMainEvent event);


    UserMainView getView();
}