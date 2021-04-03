package com.example.lendingapp.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.lendingapp.Credentials.SignUpActivity;
import com.example.lendingapp.R;

public class SplashPage extends AppCompatActivity {

    private ImageView ivSplash;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        ivSplash = findViewById(R.id.icSplash);

        animation = AnimationUtils.loadAnimation(this,R.anim.splash_animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashPage.this, LandingPages.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        ivSplash.startAnimation(animation);
    }
}