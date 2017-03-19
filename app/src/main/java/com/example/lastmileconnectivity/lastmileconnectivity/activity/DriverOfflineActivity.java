package com.example.lastmileconnectivity.lastmileconnectivity.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lastmileconnectivity.lastmileconnectivity.R;
import com.kelltontech.ui.activity.BaseActivity;
import com.kelltontech.utils.StringUtils;

public class DriverOfflineActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_mobile1;
    private Button btn_submit1;
    private EditText et_mobile2;
    private Button btn_submit2;
    private EditText et_mobile3;
    private Button btn_submit3;
    private EditText et_mobile4;
    private Button btn_submit4;
    private Button btn_work_offline;
    private View ll_start_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_offline);
        setupToolbar();


        et_mobile1 = (EditText) findViewById(R.id.et_mobile1);
        et_mobile2 = (EditText) findViewById(R.id.et_mobile2);
        et_mobile3 = (EditText) findViewById(R.id.et_mobile3);
        et_mobile4 = (EditText) findViewById(R.id.et_mobile4);

        btn_submit1 = (Button) findViewById(R.id.btn_submit1);
        btn_submit2 = (Button) findViewById(R.id.btn_submit2);
        btn_submit3 = (Button) findViewById(R.id.btn_submit3);
        btn_submit4 = (Button) findViewById(R.id.btn_submit4);

        btn_work_offline = (Button) findViewById(R.id.btn_work_offline);
        ll_start_layout = findViewById(R.id.ll_start_layout);

        btn_work_offline.setOnClickListener(this);
        btn_submit1.setOnClickListener(this);
        btn_submit2.setOnClickListener(this);
        btn_submit3.setOnClickListener(this);
        btn_submit4.setOnClickListener(this);

    }

    void setupToolbar() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit1:

                if(StringUtils.isNullOrEmpty(et_mobile1.getText().toString())){
                    Toast.makeText(this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }else if(et_mobile1.getText().toString().equals("1234512345")){
                    Toast.makeText(this, "This mobile is not registered with wallet", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(btn_submit1.getText().toString().equals("Finish Ride")){
                    Toast.makeText(DriverOfflineActivity.this, "Ride finished successfully", Toast.LENGTH_SHORT).show();
                    btn_submit1.setText("Send OTP");
                    et_mobile1.setEnabled(true);
                    et_mobile1.setText("");
                }else{
                    showAlert(v);
                }
                break;

            case R.id.btn_submit2:
                if(StringUtils.isNullOrEmpty(et_mobile2.getText().toString())){
                    Toast.makeText(this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }else if(et_mobile1.getText().toString().equals("1234512345")){
                    Toast.makeText(this, "This mobile is not registered with wallet", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(btn_submit2.getText().toString().equals("Finish Ride")){
                    Toast.makeText(DriverOfflineActivity.this, "Ride finished successfully", Toast.LENGTH_SHORT).show();
                    btn_submit2.setText("Send OTP");
                    et_mobile2.setEnabled(true);
                    et_mobile2.setText("");
                }else{
                    showAlert(v);
                }
                break;

            case R.id.btn_submit3:
                if(StringUtils.isNullOrEmpty(et_mobile3.getText().toString())){
                    Toast.makeText(this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }else if(et_mobile1.getText().toString().equals("1234512345")){
                    Toast.makeText(this, "This mobile is not registered with wallet", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(btn_submit3.getText().toString().equals("Finish Ride")){
                    Toast.makeText(DriverOfflineActivity.this, "Ride finished successfully", Toast.LENGTH_SHORT).show();
                    btn_submit3.setText("Send OTP");
                    et_mobile3.setEnabled(true);
                    et_mobile3.setText("");
                }else{
                    showAlert(v);
                }
                break;

            case R.id.btn_submit4:
                if(StringUtils.isNullOrEmpty(et_mobile4.getText().toString())){
                    Toast.makeText(this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }else if(et_mobile1.getText().toString().equals("1234512345")){
                    Toast.makeText(this, "This mobile is not registered with wallet", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(btn_submit4.getText().toString().equals("Finish Ride")){
                    Toast.makeText(DriverOfflineActivity.this, "Ride finished successfully", Toast.LENGTH_SHORT).show();
                    btn_submit4.setText("Send OTP");
                    et_mobile4.setEnabled(true);
                    et_mobile4.setText("");
                }else{
                    showAlert(v);
                }
                break;

            case R.id.btn_work_offline:
                btn_work_offline.setVisibility(View.GONE);
                ll_start_layout.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void showAlert(final View view) {

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.otp_layout);
        dialog.setTitle("Enter OTP");

        final EditText text = (EditText) dialog.findViewById(R.id.et_otp);
        Button btn_submit = (Button) dialog.findViewById(R.id.btn_submit);
        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp=text.getText().toString();
                if(StringUtils.isNullOrEmpty(otp)){
                    Toast.makeText(DriverOfflineActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                }else if(otp.equals("1234")){
                    switch (view.getId()) {
                        case R.id.btn_submit1:
                            btn_submit1.setText("Finish Ride");
                            et_mobile1.setEnabled(false);
                            Toast.makeText(DriverOfflineActivity.this, "Ride started successfully", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.btn_submit2:
                            btn_submit2.setText("Finish Ride");
                            et_mobile2.setEnabled(false);
                            Toast.makeText(DriverOfflineActivity.this, "Ride started successfully", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.btn_submit3:
                            btn_submit3.setText("Finish Ride");
                            et_mobile3.setEnabled(false);
                            Toast.makeText(DriverOfflineActivity.this, "Ride started successfully", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.btn_submit4:
                            btn_submit4.setText("Finish Ride");
                            et_mobile4.setEnabled(false);
                            Toast.makeText(DriverOfflineActivity.this, "Ride started successfully", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    dialog.dismiss();
                }else{
                    Toast.makeText(DriverOfflineActivity.this, "Enter Valid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
