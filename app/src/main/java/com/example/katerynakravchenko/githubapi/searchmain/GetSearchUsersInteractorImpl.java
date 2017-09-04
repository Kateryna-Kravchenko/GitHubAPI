package com.example.katerynakravchenko.githubapi.searchmain;

/**
 * Created by katerynakravchenko on 28.07.17.
 */


public class GetSearchUsersInteractorImpl implements GetSearchUsersInteractor {
    SearchMainRepository repository;

    public GetSearchUsersInteractorImpl(SearchMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String search) {
        repository.setSearchText(search);
        repository.getSearchUsers();
    }
}