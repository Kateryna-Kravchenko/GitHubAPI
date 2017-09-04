package com.example.katerynakravchenko.githubapi.detailsscreen;

import com.example.katerynakravchenko.githubapi.api.GitHubService;
import com.example.katerynakravchenko.githubapi.detailsscreen.events.UserMainEvent;
import com.example.katerynakravchenko.githubapi.api.DetailsUser;
import com.example.katerynakravchenko.githubapi.libs.base.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by katerynakravchenko on 28.07.17.
 */

public class UserMainRepositoryImpl implements UserMainRepository {

    private String url;

    private EventBus eventBus;
    private GitHubService service;

    public UserMainRepositoryImpl(EventBus eventBus, GitHubService service) {
        this.eventBus = eventBus;
        this.service = service;
    }


    @Override
    public void setUserURL(String SearchText) {
        this.url = SearchText;

    }

    @Override
    public void getUserDetails() {
        Call<DetailsUser> call = service.detailsScreen(url);
        Callback<DetailsUser> callback = new Callback<DetailsUser>() {
            @Override
            public void onResponse(Call<DetailsUser> call, Response<DetailsUser> response) {
                if (response.isSuccess()) {
                    DetailsUser recipeSearchResponse = response.body();

                    DetailsUser users = recipeSearchResponse;
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
            public void onFailure(Call<DetailsUser> call, Throwable t) {
                post(t.getLocalizedMessage());
            }
        };

        call.enqueue(callback);
    }


    private void post(String error, DetailsUser user) {
        UserMainEvent event = new UserMainEvent();
        event.setError(error);
        event.setUser(user);
        eventBus.post(event);
    }

    private void post(DetailsUser user) {
        post(null, user);
    }

    private void post(String error) {
        post(error, null);
    }


}


