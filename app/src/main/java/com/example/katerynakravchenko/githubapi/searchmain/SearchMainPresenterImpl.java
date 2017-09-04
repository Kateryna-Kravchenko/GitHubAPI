package com.example.katerynakravchenko.githubapi.searchmain;

import com.example.katerynakravchenko.githubapi.libs.base.EventBus;
import com.example.katerynakravchenko.githubapi.searchmain.events.SearchMainEvent;
import com.example.katerynakravchenko.githubapi.searchmain.ui.SearchMainView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by katerynakravchenko on 28.07.17.
 */

public class SearchMainPresenterImpl implements SearchMainPresenter {

    private EventBus eventBus;
    private SearchMainView view;
    GetSearchUsersInteractor geSearchUsersInteractor;

    public SearchMainPresenterImpl(EventBus eventBus, SearchMainView view, GetSearchUsersInteractor geSearchUsersInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.geSearchUsersInteractor = geSearchUsersInteractor;
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

    public void onEventMainThread(SearchMainEvent event) {
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
    public void getNextSearch(String search) {
        if (this.view != null) {
            view.hideUIElements();
            view.showProgress();
        }
        geSearchUsersInteractor.execute( search);
    }


    @Override
    public void UsersReady( ) {
        if (this.view != null) {
            view.hideProgress();
            view.showUIElements();
        }
    }

    @Override
    public void UsersError(String error) {
        if (this.view != null) {
            view.onGetUsersError(error);
        }
    }

    @Override
    public SearchMainView getView() {
        return this.view;
    }
}