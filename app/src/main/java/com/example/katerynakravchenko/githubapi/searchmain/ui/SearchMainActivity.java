package com.example.katerynakravchenko.githubapi.searchmain.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.katerynakravchenko.githubapi.GitSearchApp;
import com.example.katerynakravchenko.githubapi.R;
import com.example.katerynakravchenko.githubapi.detailsscreen.ui.UserMainActivity;
import com.example.katerynakravchenko.githubapi.entities.User;
import com.example.katerynakravchenko.githubapi.searchmain.SearchMainPresenter;
import com.example.katerynakravchenko.githubapi.searchmain.di.SearchMainComponent;
import com.example.katerynakravchenko.githubapi.searchmain.ui.adapters.OnItemClickListener;
import com.example.katerynakravchenko.githubapi.searchmain.ui.adapters.UsersAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchMainActivity extends AppCompatActivity implements SearchMainView, OnItemClickListener {


    @Bind(R.id.img_search)
    ImageView imgSearch;
    @Bind(R.id.ll_image)
    LinearLayout llImage;
    @Bind(R.id.ll_progres)
    LinearLayout ll_progres;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.recycler_search)
    RecyclerView recyclerSearch;
    @Bind(R.id.rl_plug_search)
    RelativeLayout rlPlugSearch;


    private SearchMainPresenter presenter;
    private SearchMainComponent component;
    private UsersAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupInjection();
        setupRecyclerView();
        presenter.onCreate();
        etSearch.setOnKeyListener(OnKeyListener);

    }


    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }


    private void setupRecyclerView() {

        recyclerSearch.setLayoutManager(new LinearLayoutManager(this));
        recyclerSearch.setAdapter(adapter);
    }

    private void setupInjection() {
        GitSearchApp app = (GitSearchApp) getApplication();
        component = app.getSearchMainComponent(this, this, this);
        presenter = getPresenter();
        adapter = getAdapter();
    }

    @Override
    public void showProgress() {
        ll_progres.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        ll_progres.setVisibility(View.GONE);
    }

    @Override
    public void showUIElements() {
        recyclerSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUIElements() {
        recyclerSearch.setVisibility(View.GONE);
    }


    @Override
    public void setUsers(List<User> users) {
        adapter.setUsers(users);
        if (users.size() == 0) {
            Toast.makeText(this, "Nothing found, try another word", Toast.LENGTH_SHORT).show();
        }
        showUIElements();
        hideProgress();
    }

    @Override
    public void onGetUsersError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    public SearchMainPresenter getPresenter() {
        return component.getPresenter();
    }

    @Override
    public void onItemClick(User user) {

        UserMainActivity.newInstance(this, user.getUrl());
    }

    public UsersAdapter getAdapter() {
        return component.getAdapter();
    }


    @OnClick(R.id.ll_image)
    public void onToolBarClick() {
        presenter.getNextSearch(etSearch.getText().toString());
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(llImage.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }


    View.OnKeyListener OnKeyListener = new View.OnKeyListener() {
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if (event.getAction() == KeyEvent.ACTION_DOWN) {

                switch (keyCode) {

                    case KeyEvent.KEYCODE_DPAD_CENTER:
                    case KeyEvent.KEYCODE_ENTER:
                        presenter.getNextSearch(etSearch.getText().toString());
                        return true;
                    default:
                        break;
                }
            }
            return false;
        }
    };
}