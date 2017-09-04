package com.example.katerynakravchenko.githubapi.searchmain;

import com.example.katerynakravchenko.githubapi.searchmain.events.SearchMainEvent;
import com.example.katerynakravchenko.githubapi.searchmain.ui.SearchMainView;

/**
 * Created by katerynakravchenko on 18.07.17.
 */

public interface SearchMainPresenter {
    void onCreate();

    void onDestroy();

    void getNextSearch(String search);

    void UsersError(String error);

    void UsersReady();

    void onEventMainThread(SearchMainEvent event);

    SearchMainView getView();
}