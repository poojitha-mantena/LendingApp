package com.example.lendingapp.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.lendingapp.Credentials.SignUpActivity;
import com.example.lendingapp.Dashboard.SelectCategoryPage;
import com.example.lendingapp.R;

public class SplashPage extends AppCompatActivity {

    private static int SPLASH_TIMER = 3000;

    private ImageView ivSplash;
    private Animation animation;

    SharedPreferences landingPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        ivSplash = findViewById(R.id.icSplash);

        animation = AnimationUtils.loadAnimation(this,R.anim.splash_animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                landingPages = getSharedPreferences("landingPages",MODE_PRIVATE);
                boolean isFirstTime = landingPages.getBoolean("firstTime",true);

                if(isFirstTime){

                    SharedPreferences.Editor editor = landingPages.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), LandingPages.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), SelectCategoryPage.class);
                    startActivity(intent);
                    finish();
                }

            }
        },SPLASH_TIMER);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

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