package com.example.katerynakravchenko.githubapi.detailsscreen.di;


import com.example.katerynakravchenko.githubapi.detailsscreen.UserMainPresenter;
import com.example.katerynakravchenko.githubapi.libs.base.ImageLoader;
import com.example.katerynakravchenko.githubapi.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {UserMainModule.class, LibsModule.class})
public interface UserMainComponent {
    ImageLoader getImageLoader();

    UserMainPresenter getPresenter();
}