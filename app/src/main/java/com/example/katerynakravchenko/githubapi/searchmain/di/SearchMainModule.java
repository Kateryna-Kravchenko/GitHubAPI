package com.example.katerynakravchenko.githubapi.searchmain.di;


import com.example.katerynakravchenko.githubapi.api.GitHubSearchClient;
import com.example.katerynakravchenko.githubapi.api.GitHubService;
import com.example.katerynakravchenko.githubapi.entities.User;
import com.example.katerynakravchenko.githubapi.libs.base.EventBus;
import com.example.katerynakravchenko.githubapi.libs.base.ImageLoader;
import com.example.katerynakravchenko.githubapi.searchmain.GetSearchUsersInteractor;
import com.example.katerynakravchenko.githubapi.searchmain.GetSearchUsersInteractorImpl;
import com.example.katerynakravchenko.githubapi.searchmain.SearchMainPresenter;
import com.example.katerynakravchenko.githubapi.searchmain.SearchMainPresenterImpl;
import com.example.katerynakravchenko.githubapi.searchmain.SearchMainRepository;
import com.example.katerynakravchenko.githubapi.searchmain.SearchMainRepositoryImpl;
import com.example.katerynakravchenko.githubapi.searchmain.ui.SearchMainView;
import com.example.katerynakravchenko.githubapi.searchmain.ui.adapters.OnItemClickListener;
import com.example.katerynakravchenko.githubapi.searchmain.ui.adapters.UsersAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class SearchMainModule {

    SearchMainView view;
    OnItemClickListener clickListener;

    public SearchMainModule(SearchMainView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    SearchMainView providesSearchMainView() {
        return this.view;
    }

    @Provides
    @Singleton
    SearchMainPresenter providesRecipeMainPresenter(EventBus eventBus, SearchMainView view, GetSearchUsersInteractor getSearchInteractor) {
        return new SearchMainPresenterImpl(eventBus, view, getSearchInteractor);
    }

    @Provides
    @Singleton
    GetSearchUsersInteractor providesSaveRecipeInteractor(SearchMainRepository repository) {
        return new GetSearchUsersInteractorImpl(repository);
    }


    @Provides
    @Singleton
    SearchMainRepository providesRecipeMainRepository(EventBus eventBus, GitHubService service) {
        return new SearchMainRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    GitHubService providesRecipeService() {
        return new GitHubSearchClient().getSearchService();
    }


    @Provides
    @Singleton
    UsersAdapter providesRecipesAdapter(ArrayList<User> recipeList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        return new UsersAdapter(recipeList, imageLoader, onItemClickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.clickListener;
    }

    @Provides
    @Singleton
    ArrayList<User> providesEmptyList() {
        return new ArrayList<User>();
    }

}
