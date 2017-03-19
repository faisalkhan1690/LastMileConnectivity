package com.example.lastmileconnectivity.lastmileconnectivity.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastmileconnectivity.lastmileconnectivity.R;
import com.example.lastmileconnectivity.lastmileconnectivity.constant.SharePref;
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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DriverHomeActivity.this);
                alertDialogBuilder.setTitle("Booking Request");
                alertDialogBuilder
                        .setMessage("One user is requesting for ride. Would you like to accept his request?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                startActivity(new Intent(DriverHomeActivity.this,UserRideActivity.class));

                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        },5000);



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
                SharePref.setUserLoggedIn(this,false);
                Intent intent = new Intent(DriverHomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            case R.id.iv_account_info:
                Toast.makeText(this, "Man at work", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_history:
                Toast.makeText(this, "Man at work", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_wallet_status:
                Toast.makeText(this, "Man at work", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_data_statistics:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://prettilypetite.com/Nagarro/pages/bar_view.php"));
                startActivity(browserIntent);
                break;

        }
    }

    public void setData(){
        mTvName.setText("Hello Rajesh");
    }
}
