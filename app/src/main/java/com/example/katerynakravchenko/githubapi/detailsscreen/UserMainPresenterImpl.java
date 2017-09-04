package com.example.katerynakravchenko.githubapi.detailsscreen;

import com.example.katerynakravchenko.githubapi.detailsscreen.events.UserMainEvent;
import com.example.katerynakravchenko.githubapi.detailsscreen.ui.UserMainView;
import com.example.katerynakravchenko.githubapi.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by katerynakravchenko on 28.07.17.
 */

public class UserMainPresenterImpl implements UserMainPresenter {

    private EventBus eventBus;
    private UserMainView view;
    UserInteractor getUsersInteractor;

    public UserMainPresenterImpl(EventBus eventBus, UserMainView view, UserInteractor geSearchUsersInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.getUsersInteractor = geSearchUsersInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }


    @Override
    @Subscribe

    public void onEventMainThread(UserMainEvent event) {
        if (this.view != null) {
            String error = event.getError();
            if (error != null) {
                view.hideProgress();
                view.onGetUsersError(error);
            } else {
                view.setUsers(event.getUser());
            }
        }
    }

    @Override
    public void getUserDetails(String search) {
        if (this.view != null) {
            view.hideUIElements();
            view.showProgress();
        }
        getUsersInteractor.execute(search);
    }


    @Override
    public void UsersError(String error) {
        if (this.view != null) {
            view.onGetUsersError(error);
        }
    }

    @Override
    public UserMainView getView() {
        return this.view;
    }
}