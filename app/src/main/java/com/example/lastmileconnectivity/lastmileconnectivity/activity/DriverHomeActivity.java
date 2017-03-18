package com.example.lastmileconnectivity.lastmileconnectivity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lastmileconnectivity.lastmileconnectivity.R;
import com.kelltontech.ui.activity.BaseActivity;

public class DriverHomeActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvName;
    private TextView mTvSignOut;
    private ImageView mIvAccountInfo;
    private ImageView mIvHistory;
    private ImageView mIvWalletStatus;
    private ImageView mIvDataStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);

        mTvName=(TextView)findViewById(R.id.tv_name);
        mTvSignOut=(TextView)findViewById(R.id.tv_sign_out);

        mIvAccountInfo=(ImageView)findViewById(R.id.iv_account_info);
        mIvHistory=(ImageView)findViewById(R.id.iv_history);
        mIvWalletStatus=(ImageView)findViewById(R.id.iv_wallet_status);
        mIvDataStatistics=(ImageView)findViewById(R.id.iv_data_statistics);

        mTvSignOut.setOnClickListener(this);
        mIvAccountInfo.setOnClickListener(this);
        mIvHistory.setOnClickListener(this);
        mIvWalletStatus.setOnClickListener(this);
        mIvDataStatistics.setOnClickListener(this);
        setData();

    }

    @Override
    public void getData(int actionID) {

    }

    @Override
    public void updateUi(boolean status, int action, Object serviceResponse) {

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_sign_out:
                Intent intent = new Intent(DriverHomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                //TODO web hit
                break;

            case R.id.iv_account_info:
                //TODO web hit
                break;

            case R.id.iv_history:
                //TODO web hit
                break;

            case R.id.iv_wallet_status:
                //TODO web hit
                break;

            case R.id.iv_data_statistics:
                //TODO web hit
                break;

        }
    }

    public void setData(){
        mTvName.setText("Hello Rajesh");
    }
}
