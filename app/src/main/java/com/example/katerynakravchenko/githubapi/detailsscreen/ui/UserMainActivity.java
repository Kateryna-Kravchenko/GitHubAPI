package com.example.katerynakravchenko.githubapi.detailsscreen.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.katerynakravchenko.githubapi.GitSearchApp;
import com.example.katerynakravchenko.githubapi.R;
import com.example.katerynakravchenko.githubapi.detailsscreen.UserMainPresenter;
import com.example.katerynakravchenko.githubapi.detailsscreen.di.UserMainComponent;
import com.example.katerynakravchenko.githubapi.api.DetailsUser;

import butterknife.Bind;
import butterknife.ButterKnife;


public class UserMainActivity extends AppCompatActivity implements UserMainView {



//    @Bind(R.id.img_user)
//    ImageView imgUser;
//    @Bind(R.id.tv_user_name)
//    TextView tvUserName;
//    @Bind(R.id.tv_email)
//    TextView tvEmail;
//    @Bind(R.id.tv_repos_url)
//    TextView tvReposUrl;
//    @Bind(R.id.tv_bio)
//    TextView tvBio;
//     @Bind(R.id.ns_details_user)
//    NestedScrollView ns_details_user;


    @Bind(R.id.wv_detals)
    WebView wv_detals;
    @Bind(R.id.ll_progres)
    LinearLayout ll_progres;

    private UserMainPresenter presenter;
    private UserMainComponent component;
    private DetailsUser currentUser;

    public static void newInstance(Context context, String url) {
        Intent intent = new Intent(context, UserMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        setupInjection();
        presenter.onCreate();
        Bundle extras = getIntent().getExtras();
        if (extras == null) {

        } else {
            if (extras.getString("url") != null) {
                presenter.getUserDetails(extras.getString("url"));
                Log.d("url", "url " + extras.getString("url"));
            }
        }
    }


    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }


    private void setupInjection() {
        GitSearchApp app = (GitSearchApp) getApplication();
        component = app.getUserMainComponent(this, this);
        presenter = getPresenter();

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
        wv_detals.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUIElements() {
        wv_detals.setVisibility(View.GONE);
    }

    @Override
    public void setUsers(DetailsUser user) {
        currentUser = user;
//        tvUserName.setText(currentUser.getName());
//        tvEmail.setText(currentUser.getEmail());
//        tvReposUrl.setText(currentUser.getHtmlUrl());
//        tvBio.setText(currentUser.getBio());
//        ns_details_user.setVisibility(View.VISIBLE);

        wv_detals.setWebViewClient(new MyWebViewClient());
        wv_detals.loadUrl(currentUser.getHtmlUrl());
        hideProgress();
        showUIElements();

    }


    @Override
    public void onGetUsersError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    public UserMainPresenter getPresenter() {
        return component.getPresenter();
    }


    private class MyWebViewClient extends WebViewClient {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }

}

