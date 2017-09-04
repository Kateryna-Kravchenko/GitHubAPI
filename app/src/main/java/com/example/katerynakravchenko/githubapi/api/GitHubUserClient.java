package com.example.katerynakravchenko.githubapi.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by katerynakravchenko on 01.09.17.
 */

public class GitHubUserClient {
    private Retrofit retrofit;
    private final static String BASE_URL = "https://github.com/";


    public GitHubUserClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public GitHubService getUserService(){
        return this.retrofit.create(GitHubService.class);
    }
}
