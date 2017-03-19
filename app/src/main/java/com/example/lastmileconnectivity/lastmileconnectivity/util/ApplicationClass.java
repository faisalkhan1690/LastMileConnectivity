package com.example.lastmileconnectivity.lastmileconnectivity.util;

import android.content.Context;

import com.example.lastmileconnectivity.lastmileconnectivity.R;
import com.kelltontech.application.BaseApplication;
import com.kelltontech.volley.ext.RequestManager;

/**
 * <h1><font color="orange">ApplicationClass</font></h1>
 * Application class for Club Mahindra project. This class contains all the reference those are required on Application level
 * And also initialize object those are required by application starts and should clear as soon as application close.
 *
 * @author Faisal khan
 */
public class ApplicationClass extends BaseApplication {


    private RequestManager mRequestManager;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestManager = RequestManager.initializeWith(this.getApplicationContext(), new RequestManager.Config(getString(R.string.data_data_DSD_pics), 5242880, 4));
    }

    @Override
    protected void initialize() {

    }

    public RequestManager getRequestManager() {
        return mRequestManager;
    }


}
