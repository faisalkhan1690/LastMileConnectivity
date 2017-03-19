package com.example.lastmileconnectivity.lastmileconnectivity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastmileconnectivity.lastmileconnectivity.R;
import com.example.lastmileconnectivity.lastmileconnectivity.constant.IAppConstant;
import com.example.lastmileconnectivity.lastmileconnectivity.constant.IWebServcies;
import com.example.lastmileconnectivity.lastmileconnectivity.constant.SharePref;
import com.example.lastmileconnectivity.lastmileconnectivity.model.LoginModel;
import com.example.lastmileconnectivity.lastmileconnectivity.util.CustomUtil;
import com.example.lastmileconnectivity.lastmileconnectivity.util.VolleyErrorListener;
import com.kelltontech.ui.activity.BaseActivity;
import com.kelltontech.utils.ConnectivityUtils;
import com.kelltontech.utils.StringUtils;
import com.kelltontech.volley.ext.GsonObjectRequest;
import com.kelltontech.volley.ext.RequestManager;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private View mViewRoot;
    private EditText mEtUserId;
    private EditText mEtUserPassword;
    private TextView mTvHideShow;
    private boolean mIsPasswordVisible = false;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpToolBar();

        //view inti
        mViewRoot=findViewById(R.id.root_view);
        mEtUserId=(EditText)findViewById(R.id.et_user_id);
        mEtUserPassword=(EditText)findViewById(R.id.et_user_password);

        mTvHideShow=(TextView) findViewById(R.id.tv_hide_show);
        View viewLogin = findViewById(R.id.btn_login);
        View viewRegister = findViewById(R.id.tv_register);

        //on click listener
        mTvHideShow.setOnClickListener(this);
        assert viewLogin != null;
        viewLogin.setOnClickListener(this);
        assert viewRegister != null;
        viewRegister.setOnClickListener(this);

    }


    /**
     * Initialize Toolbar and set in the action bar
     */
    private void setUpToolBar() {

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        assert mToolbar != null;
        TextView tv_title = (TextView) mToolbar.findViewById(R.id.tv_title);
        assert tv_title != null;
        tv_title.setText(R.string.user_login);
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
        switch (actionID){
            case IAppConstant.LOGIN:
                String url= IWebServcies.LOGIN+"phno="+username+"&paswd="+password;
                showProgressDialog();
                RequestManager.addRequest(new GsonObjectRequest<LoginModel>(url, new HashMap<String, String>(), null,
                        LoginModel.class, new VolleyErrorListener(this, IAppConstant.LOGIN)) {
                    @Override
                    protected void deliverResponse(LoginModel response) {
                        updateUi(true, IAppConstant.LOGIN, response);
                    }
                });
        }

    }

    @Override
    public void updateUi(boolean status, int action, Object serviceResponse) {
        switch (action) {
            case IAppConstant.LOGIN:
                if (status) {
                    LoginModel loginModel = (LoginModel) serviceResponse;
                    if(loginModel.status!=null && loginModel.status.equals("success")){
                        Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        SharePref.setUserLoggedIn(this,true);
                        SharePref.setUserName(this,username);

                        if(loginModel.access!=null && loginModel.access.equals("1")){
                            SharePref.setIsDriver(this,true);
                            startActivity(new Intent(LoginActivity.this,DriverHomeActivity.class));
                        }else{
                            SharePref.setIsDriver(this,false);
                            startActivity(new Intent(LoginActivity.this,UserHomeActivity.class));
                        }
                        finish();
                    }else{
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                    removeProgressDialog();
                } else {
                    removeProgressDialog();
                }

                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.btn_login:
                username=mEtUserId.getText().toString();
                password=mEtUserPassword.getText().toString();

                if (!ConnectivityUtils.isNetworkEnabled(LoginActivity.this)) {
                    CustomUtil.showSnackBar(mViewRoot,getString(R.string.internet_check));
                    return;
                }

                if(checkValidation(username,password)){
                    getData(IAppConstant.LOGIN);
                }
                break;

            case R.id.tv_hide_show:

                if (mIsPasswordVisible) {
                    mIsPasswordVisible = false;

                    //setting text type password
                    mEtUserPassword.setTransformationMethod(new PasswordTransformationMethod());
                    mTvHideShow.setText(getString(R.string.show));
                } else {
                    mIsPasswordVisible = true;

                    //setting text type normal
                    mEtUserPassword.setTransformationMethod(null);
                    mTvHideShow.setText(getString(R.string.hide));
                }
                mEtUserPassword.setSelection(mEtUserPassword.getText().toString().length());
                break;

            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
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
