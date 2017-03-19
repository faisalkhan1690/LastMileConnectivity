/*
 *
 *  Proprietary and confidential. Property of Kellton Tech Solutions Ltd. Do not disclose or distribute.
 *  You must have written permission from Kellton Tech Solutions Ltd. to use this code.
 *
 */

package com.kelltontech.ui.activity;

import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;

import com.kelltontech.BuildConfig;
import com.kelltontech.R;
import com.kelltontech.application.BaseApplication;
import com.kelltontech.ui.IScreen;
import com.kelltontech.utils.KeypadUtils;


/**
 * This class is used as base-class for application-base-activity.
 */
public abstract class BaseActivity extends AppCompatActivity implements IScreen {

    private String LOG_TAG = getClass().getSimpleName();
    public static final String COMING_FROM_NOTIFICATION = "COMING_FROM_NOTIFICATION";
    public static final String NOTIFICATION_TYPE_BROADCAST = "NOTIFICATION_TYPE_BROADCAST";
    public static final String PUSH_MODEL = "PUSH_MODEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(COMING_FROM_NOTIFICATION) && bundle.containsKey(NOTIFICATION_TYPE_BROADCAST)
                && bundle.containsKey(PUSH_MODEL)) {

            Intent intent = new Intent("NOTIFICATION_CLICK_INTENT_FILTER");
            bundle.putString(NOTIFICATION_TYPE_BROADCAST, bundle.getString(NOTIFICATION_TYPE_BROADCAST));
            intent.putExtras(bundle);
            sendBroadcast(intent);
        }
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) {
            Log.i(LOG_TAG, "onResume()");
        }

        Application application = this.getApplication();
        if (application instanceof BaseApplication) {
            BaseApplication baseApplication = (BaseApplication) application;
            if (baseApplication.isAppInBackground()) {
                onAppResumeFromBackground();
            }
            baseApplication.onActivityResumed();
        }
    }

    /**
     * This callback will be called after onResume if application is being
     * resumed from background. <br/>
     * <p/>
     * Subclasses can override this method to get this callback.
     */
    protected void onAppResumeFromBackground() {
        if (BuildConfig.DEBUG) {
            Log.i(LOG_TAG, "onAppResumeFromBackground()");
        }
    }

    /**
     * This method should be called to force app assume itself not in
     * background.
     */
    public final void setAppNotInBackground() {
        Application application = this.getApplication();
        if (application instanceof BaseApplication) {
            BaseApplication baseApplication = (BaseApplication) application;
            baseApplication.setAppInBackground(false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) {
            Log.i(LOG_TAG, "onPause()");
        }

        Application application = this.getApplication();
        if (application instanceof BaseApplication) {
            BaseApplication baseApplication = (BaseApplication) application;
            baseApplication.onActivityPaused();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (BuildConfig.DEBUG) {
            Log.i(LOG_TAG, "onNewIntent()");
        }
    }

    /**
     * Subclass should over-ride this method to update the UI with response,
     * this base class promises to call this method from UI thread.
     *
     * @param serviceResponse
     */
    public abstract void updateUi(final boolean status, final int action, final Object serviceResponse);

    // ////////////////////////////// show and hide ProgressDialog

//    private ProgressDialog mProgressDialog;
//
//    /**
//     * Shows a simple native progress dialog<br/>
//     * Subclass can override below two methods for custom dialogs- <br/>
//     * 1. showProgressDialog <br/>
//     * 2. removeProgressDialog
//     *
//     * @param bodyText
//     */
//    public void showProgressDialog(String bodyText) {
//        if (isFinishing()) {
//            return;
//        }
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(BaseActivity.this);
//            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            mProgressDialog.setCancelable(false);
//            mProgressDialog.setOnKeyListener(new Dialog.OnKeyListener() {
//                @Override
//                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                    if (keyCode == KeyEvent.KEYCODE_CAMERA || keyCode == KeyEvent.KEYCODE_SEARCH) {
//                        return true; //
//                    }
//                    return false;
//                }
//            });
//        }
//
//        mProgressDialog.setMessage(bodyText);
//
//        if (!mProgressDialog.isShowing()) {
//            mProgressDialog.show();
//        }
//    }
//
//    /**
//     * Removes the simple native progress dialog shown via showProgressDialog <br/>
//     * Subclass can override below two methods for custom dialogs- <br/>
//     * 1. showProgressDialog <br/>
//     * 2. removeProgressDialog
//     */
//    public void removeProgressDialog() {
//        try {
//            if (mProgressDialog != null && mProgressDialog.isShowing()) {
//                mProgressDialog.dismiss();
//            }
//        } catch (Exception e) {
//
//        }
//
//    }
//
//
//
//    public void setProgressDialog(ProgressDialog dialog) {
//        this.mProgressDialog = dialog;
//    }
    // ////////////////////////////// show and hide key-board


//    private PopupWindow mPpoPopupWindow;
//
//    private void popupProgressInit() {
//        mPpoPopupWindow= OtherUtill.showProgressDialog(this);
//    }
//
//    public void showProgressDialog(String message) {
//        if (isFinishing()) {
//            return;
//        }
//        if (mPpoPopupWindow == null) {
//           popupProgressInit();
//        }
//
//        if (!mPpoPopupWindow.isShowing()) {
//            mPpoPopupWindow.showAtLocation(getWindow().getDecorView().getRootView(),Gravity.CENTER,0,0);
//        }
//    }
//
//    /**
//     * Removes the simple native progress dialog shown via showProgressDialog <br/>
//     * Subclass can override below two methods for custom dialogs- <br/>
//     * 1. showProgressDialog <br/>
//     * 2. removeProgressDialog
//     */
//    public void removeProgressDialog() {
//        try {
//            if (mPpoPopupWindow != null && mPpoPopupWindow.isShowing()) {
//                mPpoPopupWindow.dismiss();
//            }
//        } catch (Exception e) {
//
//        }
//
//    }

//    @Override
//    public void onBackPressed() {
//        if(mPpoPopupWindow==null || !mPpoPopupWindow.isShowing()){
//            super.onBackPressed();
//        }
//    }

    ProgressDialog progressDialog;

    public void showProgressDialog() {
        try {
            if (progressDialog == null) {
                progressDialog = ProgressDialog.show(this, "", "Loading...");
            } else {
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void removeProgressDialog() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        try {
            View v = getCurrentFocus();
            boolean ret = super.dispatchTouchEvent(event);

            if (v instanceof EditText) {
                View w = getCurrentFocus();
                int scrcoords[] = new int[2];
                w.getLocationOnScreen(scrcoords);
                float x = event.getRawX() + w.getLeft() - scrcoords[0];
                float y = event.getRawY() + w.getTop() - scrcoords[1];

                if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom())) {
                    KeypadUtils.hideSoftKeypad(this);
                }
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }

    }


}