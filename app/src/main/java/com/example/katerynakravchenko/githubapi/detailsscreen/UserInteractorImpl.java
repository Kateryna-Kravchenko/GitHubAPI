package com.example.katerynakravchenko.githubapi.detailsscreen;


public class UserInteractorImpl implements UserInteractor {
    UserMainRepository repository;

    public UserInteractorImpl(UserMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String userURL) {
        repository.setUserURL(userURL);
        repository.getUserDetails();

    }
}