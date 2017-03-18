package com.example.lastmileconnectivity.lastmileconnectivity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.lastmileconnectivity.lastmileconnectivity.R;
import com.example.lastmileconnectivity.lastmileconnectivity.constant.IAppConstant;
import com.kelltontech.ui.activity.BaseActivity;


public class SplashActivity extends BaseActivity implements Animation.AnimationListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        animZoomIn.setAnimationListener(this);

        View view=findViewById(R.id.image);
        assert view != null;
        view.startAnimation(animZoomIn);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();

            }

        }, IAppConstant.SPLASH_TIME_OUT);
    }

    @Override
    public void updateUi(boolean status, int action, Object serviceResponse) {
        //no use
    }

    @Override
    public void getData(int actionID) {
        //no use
    }

    @Override
    public void onAnimationStart(Animation animation) {
        //no use
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        //no use
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        //no use
    }
}

