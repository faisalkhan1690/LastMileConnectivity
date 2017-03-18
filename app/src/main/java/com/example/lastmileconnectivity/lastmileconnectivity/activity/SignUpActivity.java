package com.example.lastmileconnectivity.lastmileconnectivity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lastmileconnectivity.lastmileconnectivity.R;
import com.example.lastmileconnectivity.lastmileconnectivity.util.CustomUtil;
import com.kelltontech.ui.activity.BaseActivity;
import com.kelltontech.utils.ConnectivityUtils;
import com.kelltontech.utils.StringUtils;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    private View mViewRoot;
    private EditText mEtUserId;
    private EditText mEtUserPassword;
    private View mViewSignUp;
    private View mViewOption;
    private boolean isUser=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setUpToolBar();

        //view inti
        mViewRoot=findViewById(R.id.root_view);
        mEtUserId=(EditText)findViewById(R.id.et_user_id);
        mEtUserPassword=(EditText)findViewById(R.id.et_user_password);
        mViewSignUp=findViewById(R.id.rl_sign_up);
        mViewOption=findViewById(R.id.ll_option);
        Button btnSignUp = (Button) findViewById(R.id.btn_proceed);
        View ivDriver = findViewById(R.id.iv_driver);
        View ivUser = findViewById(R.id.iv_user);

        assert ivDriver != null;
        ivDriver.setOnClickListener(this);
        assert ivUser != null;
        ivUser.setOnClickListener(this);


        View viewRegister = findViewById(R.id.tv_register);
        assert viewRegister != null;
        viewRegister.setOnClickListener(this);
        assert btnSignUp != null;
        btnSignUp.setOnClickListener(this);
    }


    /**
     * Initialize Toolbar and set in the action bar
     */
    private void setUpToolBar() {

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        assert mToolbar != null;
        TextView tv_title = (TextView) mToolbar.findViewById(R.id.tv_title);
        TextView tv_register = (TextView) mToolbar.findViewById(R.id.tv_register);
        tv_register.setVisibility(View.INVISIBLE);
        assert tv_title != null;
        tv_title.setText(R.string.sign_up);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.back_arrow);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            //back arrow event
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getData(int actionID) {

    }

    @Override
    public void updateUi(boolean status, int action, Object serviceResponse) {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.btn_proceed:
                String username=mEtUserId.getText().toString();
                String password=mEtUserPassword.getText().toString();

                if (!ConnectivityUtils.isNetworkEnabled(SignUpActivity.this)) {
                    CustomUtil.showSnackBar(mViewRoot,getString(R.string.internet_check));
                    return;
                }

                if(checkValidation(username,password)){
                    //TODO web service hit
                    Intent intent = new Intent(SignUpActivity.this, DriverHomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                break;
            case R.id.iv_driver:
                isUser=false;
                mViewSignUp.setVisibility(View.VISIBLE);
                mViewOption.setVisibility(View.GONE);
                break;

            case R.id.iv_user:
                isUser=true;
                isUser=false;
                mViewSignUp.setVisibility(View.VISIBLE);
                mViewOption.setVisibility(View.GONE);
                break;


        }
    }

    public boolean checkValidation(String username,String password){
        if(StringUtils.isNullOrEmpty(username) && StringUtils.isNullOrEmpty(password)){
            CustomUtil.showSnackBar(mViewRoot,"Please enter mobile number / email id  and password");
            return false;
        }else if(StringUtils.isNullOrEmpty(username)){
            CustomUtil.showSnackBar(mViewRoot,"Please enter mobile number / email id");
            return false;
        }else if(StringUtils.isNullOrEmpty(password)){
            CustomUtil.showSnackBar(mViewRoot,"Please enter password");
            return false;
        }else{
            return true;
        }
    }
}
