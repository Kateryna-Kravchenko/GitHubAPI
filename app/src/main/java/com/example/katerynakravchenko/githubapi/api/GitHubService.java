package com.example.katerynakravchenko.githubapi.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by katerynakravchenko on 14.07.17.
 */

public interface GitHubService {

    @GET("users")
    Call<GitHubSearchResponse> search(@Query("q") String q);

    @GET
    Call<DetailsUser> detailsScreen(@Url String url);
}
