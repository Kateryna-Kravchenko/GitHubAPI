package com.example.katerynakravchenko.githubapi.launchscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.katerynakravchenko.githubapi.R;
import com.example.katerynakravchenko.githubapi.searchmain.ui.SearchMainActivity;
import com.luolc.emojirain.EmojiRainLayout;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);


        // bind view
        EmojiRainLayout    mContainer = (EmojiRainLayout) findViewById(R.id.group_emoji_container);

        // add emoji sources
        mContainer.addEmoji(R.drawable.git_img_1);
        mContainer.addEmoji(R.drawable.git_img_2);
        mContainer.addEmoji(R.drawable.git_img_3);
        mContainer.addEmoji(R.drawable.git_img_4);
        mContainer.addEmoji(R.drawable.git_img_5);
        mContainer.addEmoji(R.drawable.git_img_6);

        mContainer.startDropping();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent =new Intent(LaunchActivity.this, SearchMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        }, 3000);
    }
}
