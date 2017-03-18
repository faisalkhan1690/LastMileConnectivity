package com.example.lastmileconnectivity.lastmileconnectivity.activity;

import android.os.Bundle;

import com.example.lastmileconnectivity.lastmileconnectivity.R;
import com.kelltontech.ui.activity.BaseActivity;

public class DriverHomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);
    }

    @Override
    public void getData(int actionID) {

    }

    @Override
    public void updateUi(boolean status, int action, Object serviceResponse) {

    }


}
