package com.example.katerynakravchenko.githubapi.searchmain;

import com.example.katerynakravchenko.githubapi.api.GitHubSearchResponse;
import com.example.katerynakravchenko.githubapi.api.GitHubService;
import com.example.katerynakravchenko.githubapi.entities.User;
import com.example.katerynakravchenko.githubapi.libs.base.EventBus;
import com.example.katerynakravchenko.githubapi.searchmain.events.SearchMainEvent;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by katerynakravchenko on 28.07.17.
 */

public class SearchMainRepositoryImpl implements SearchMainRepository {

    private String searchText;

    private EventBus eventBus;
    private GitHubService service;

    public SearchMainRepositoryImpl(EventBus eventBus, GitHubService service) {
        this.eventBus = eventBus;
        this.service = service;
    }


    @Override
    public void setSearchText(String SearchText) {
        this.searchText = SearchText;

    }

    @Override
    public void getSearchUsers() {
        Call<GitHubSearchResponse> call = service.search(searchText);
        Callback<GitHubSearchResponse> callback = new Callback<GitHubSearchResponse>() {
            @Override
            public void onResponse(Call<GitHubSearchResponse> call, Response<GitHubSearchResponse> response) {
                if (response.isSuccess()) {
                    GitHubSearchResponse recipeSearchResponse = response.body();

                    ArrayList<User> users = recipeSearchResponse.getUsers();
                    if (users != null) {
                        post(users);
                    } else {
                        post(response.message());
                    }
                } else {
                    post(response.message());
                }
            }

            @Override
            public void onFailure(Call<GitHubSearchResponse> call, Throwable t) {
                post(t.getLocalizedMessage());
            }
        };

        call.enqueue(callback);
    }


    private void post(String error, ArrayList<User> users) {
        SearchMainEvent event = new SearchMainEvent();
        event.setError(error);
        event.setUser(users);
        eventBus.post(event);
    }

    private void post(ArrayList<User> users) {
        post(null, users);
    }

    private void post(String error) {
        post(error, null);
    }


}


