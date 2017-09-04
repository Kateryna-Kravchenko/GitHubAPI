package com.example.katerynakravchenko.githubapi.searchmain.di;


import com.example.katerynakravchenko.githubapi.libs.di.LibsModule;
import com.example.katerynakravchenko.githubapi.searchmain.SearchMainPresenter;
import com.example.katerynakravchenko.githubapi.searchmain.ui.adapters.UsersAdapter;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {SearchMainModule.class, LibsModule.class})
public interface SearchMainComponent {
   UsersAdapter getAdapter();
    SearchMainPresenter getPresenter();
}