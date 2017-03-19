package com.example.lastmileconnectivity.lastmileconnectivity.constant;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by faisal.khan on 3/19/2017.
 */

public class SharePref {

    private static final String PREF = "PREF";
    private static final String IsDriver = "IsDriver";
    private static final String IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN";
    private static final String USER_ID = "USER_ID";

    public static void setUserLoggedIn(Context context, boolean isLoggedIn) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(IS_USER_LOGGED_IN, isLoggedIn);
            editor.apply();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    public static boolean getIsUserLoggedIn(Context context) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
            return sharedPref.getBoolean(IS_USER_LOGGED_IN,false);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static void setIsDriver(Context context, boolean isDriver) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(IsDriver, isDriver);
            editor.apply();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static boolean getIsDriver(Context context) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
            return sharedPref.getBoolean(IsDriver,false);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static void setUserName(Context context, String userName) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(USER_ID, userName);
            editor.apply();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static String getUserName(Context context) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
            return sharedPref.getString(USER_ID, "");
        } catch (NullPointerException e) {
            return "";
        }
    }
}
