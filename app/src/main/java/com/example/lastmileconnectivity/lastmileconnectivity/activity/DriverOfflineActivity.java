package com.example.lastmileconnectivity.lastmileconnectivity.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.lastmileconnectivity.lastmileconnectivity.R;
import com.kelltontech.ui.activity.BaseActivity;

public class DriverOfflineActivity extends BaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        setupToolbar();

        final Button btn_work_offline=(Button)findViewById(R.id.btn_work_offline);
        assert btn_work_offline != null;
        btn_work_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_work_offline.setVisibility(View.VISIBLE);
            }
        });

    }

    void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.back_arrow);
            actionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
            actionBar.setTitle("Home");
        }
    }


    @Override
    public void updateUi(boolean status, int action, Object serviceResponse) {

    }

    @Override
    public void getData(int actionID) {

    }
}
