package com.example.katerynakravchenko.githubapi.detailsscreen.di;


import com.example.katerynakravchenko.githubapi.api.GitHubService;
import com.example.katerynakravchenko.githubapi.api.GitHubUserClient;
import com.example.katerynakravchenko.githubapi.detailsscreen.UserInteractor;
import com.example.katerynakravchenko.githubapi.detailsscreen.UserInteractorImpl;
import com.example.katerynakravchenko.githubapi.detailsscreen.UserMainPresenter;
import com.example.katerynakravchenko.githubapi.detailsscreen.UserMainPresenterImpl;
import com.example.katerynakravchenko.githubapi.detailsscreen.UserMainRepository;
import com.example.katerynakravchenko.githubapi.detailsscreen.UserMainRepositoryImpl;
import com.example.katerynakravchenko.githubapi.detailsscreen.ui.UserMainView;
import com.example.katerynakravchenko.githubapi.libs.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class UserMainModule {

    UserMainView view;

    public UserMainModule(UserMainView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    UserMainView providesUserMainView() {
        return this.view;
    }

    @Provides
    @Singleton
    UserMainPresenter providesUserMainPresenter(EventBus eventBus, UserMainView view, UserInteractor getSearchInteractor) {
        return new UserMainPresenterImpl(eventBus, view, getSearchInteractor);
    }

    @Provides
    @Singleton
    UserInteractor providesUserInteractor(UserMainRepository repository) {
        return new UserInteractorImpl(repository);
    }


    @Provides
    @Singleton
    UserMainRepository providesUserMainRepository(EventBus eventBus, GitHubService service) {
        return new UserMainRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    GitHubService providesGitHubService() {
        return new GitHubUserClient().getUserService();
    }


}
