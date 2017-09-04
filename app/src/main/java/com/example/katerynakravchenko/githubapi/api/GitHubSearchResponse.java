package com.example.katerynakravchenko.githubapi.api;

import com.example.katerynakravchenko.githubapi.entities.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by katerynakravchenko on 14.07.17.
 */

public class GitHubSearchResponse {

    @SerializedName("total_count")
    @Expose
    public Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    public Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    public ArrayList<User> items = null;

    public ArrayList<User> getItems() {
        return items;
    }


    public ArrayList<User> getUsers() {
        return items;
    }
}