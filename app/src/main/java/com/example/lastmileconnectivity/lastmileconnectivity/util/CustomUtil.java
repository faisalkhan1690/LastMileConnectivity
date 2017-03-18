package com.example.lastmileconnectivity.lastmileconnectivity.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by faisal.khan on 3/18/2017.
 */

public class CustomUtil {

    public static void showSnackBar(View view,String text){
        Snackbar.make(view,text,Snackbar.LENGTH_LONG).show();

    }

}
