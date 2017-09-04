package com.example.katerynakravchenko.githubapi;

import android.app.Application;

import com.example.katerynakravchenko.githubapi.detailsscreen.di.DaggerUserMainComponent;
import com.example.katerynakravchenko.githubapi.detailsscreen.di.UserMainComponent;
import com.example.katerynakravchenko.githubapi.detailsscreen.di.UserMainModule;
import com.example.katerynakravchenko.githubapi.detailsscreen.ui.UserMainActivity;
import com.example.katerynakravchenko.githubapi.detailsscreen.ui.UserMainView;
import com.example.katerynakravchenko.githubapi.libs.di.LibsModule;
import com.example.katerynakravchenko.githubapi.searchmain.di.DaggerSearchMainComponent;
import com.example.katerynakravchenko.githubapi.searchmain.di.SearchMainComponent;
import com.example.katerynakravchenko.githubapi.searchmain.di.SearchMainModule;
import com.example.katerynakravchenko.githubapi.searchmain.ui.SearchMainActivity;
import com.example.katerynakravchenko.githubapi.searchmain.ui.SearchMainView;
import com.example.katerynakravchenko.githubapi.searchmain.ui.adapters.OnItemClickListener;

/**
 * Created by katerynakravchenko on 14.07.17.
 */

public class GitSearchApp  extends Application{


    public SearchMainComponent getSearchMainComponent(SearchMainActivity activity, SearchMainView view, OnItemClickListener onItemClickListener) {
        return DaggerSearchMainComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .searchMainModule(new SearchMainModule(view,onItemClickListener))
                .build();
    }


    public UserMainComponent getUserMainComponent(UserMainActivity activity, UserMainView view) {
        return DaggerUserMainComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .userMainModule(new UserMainModule(view))
                .build();
    }
}
