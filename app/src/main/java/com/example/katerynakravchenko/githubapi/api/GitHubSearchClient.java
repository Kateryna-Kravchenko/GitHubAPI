package com.example.katerynakravchenko.githubapi.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by katerynakravchenko on 14.07.17.
 */

public class GitHubSearchClient {
    private Retrofit retrofit;
    private final static String BASE_URL = "https://api.github.com/search/";

    public GitHubSearchClient() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public GitHubService getSearchService(){
        return this.retrofit.create(GitHubService.class);
    }
}
