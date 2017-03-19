package com.example.lastmileconnectivity.lastmileconnectivity.util;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.lastmileconnectivity.lastmileconnectivity.constant.IAppConstant;
import com.example.lastmileconnectivity.lastmileconnectivity.model.ErrorModel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.kelltontech.ui.IScreen;
import com.kelltontech.volley.ext.CmVolleyError;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * <h1><font color="orange">VolleyErrorListener</font></h1>
 * listener class for Volley Error listener.
 *
 * @author Somya
 */
public class VolleyErrorListener implements Response.ErrorListener {
    private final int ACTION;
    private final IScreen SCREEN;
    private final String TAG=VolleyErrorListener.class.getSimpleName();

    public VolleyErrorListener(final IScreen SCREEN, final int ACTION) {
        Log.v(TAG, "VolleyErrorListener");

        this.SCREEN = SCREEN;
        this.ACTION = ACTION;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.v(TAG, "onErrorResponse");

        // if request model is not encrypted then bypass from here to caller activity
        if(error instanceof CmVolleyError){
            CmVolleyError cmVolleyError=(CmVolleyError)error;
            SCREEN.updateUi(false, ACTION, new ErrorModel(cmVolleyError.mResponseMessage,cmVolleyError.mStatus,cmVolleyError.mResponseCode));
            return;
        }

        String str;
        int code;
        ArrayList<ErrorModel> errorModelArrayList=null;

        if (error != null && error.networkResponse != null && error.networkResponse.data != null) {

            try {
                str = new String(error.networkResponse.data, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Log.d(TAG, ex.toString());
                return;
            }

            code = error.networkResponse.statusCode;

            Log.d(TAG, "Code=" + code + " str=" + str);

            try{
                errorModelArrayList = jsonToArray(str, code);
            }catch(IllegalStateException |JsonSyntaxException e){
                Log.e(TAG,e.toString());
            }

            if (errorModelArrayList != null && errorModelArrayList.size()>0) {

                Log.d(TAG, "errorModelArrayList=!Null");
                SCREEN.updateUi(false, ACTION, errorModelArrayList.get(0));
            } else {

                Log.d(TAG, "errorModelArrayList=Null");
                if (code >= 400 && code < 500) {

                    Log.d(TAG, "ACTION = Bad Request");
                    SCREEN.updateUi(false, ACTION, "Bad Request");
                } else if (code >= 500) {

                    Log.d(TAG, "ACTION = Server error");
                    SCREEN.updateUi(false, ACTION, "Server error");
                }
            }

        } else if (SCREEN != null) {

            if (error instanceof NoConnectionError) {
                Log.d(TAG, "Response=" + error);
                try {
                    str = new String(error.networkResponse.data, "UTF-8");
                    Log.d(TAG,"VolleyError="+str);
                } catch (UnsupportedEncodingException | NullPointerException e) {
                    Log.d(TAG,e.toString());
                }


                SCREEN.updateUi(false, ACTION, IAppConstant.ERROR);
            } else{
                SCREEN.updateUi(false, ACTION,  IAppConstant.ERROR);
            }
        }
    }

    /**
     * Convert Error Json to ErrorModel
     * @param jsonInput Json in string format
     * @return ArrayList ErrorModel type ArrayList
     * @throws IllegalStateException
     */
    private ArrayList<ErrorModel> jsonToArray(String jsonInput,int statusCode) throws IllegalStateException, JsonSyntaxException {
        Log.v(TAG, "jsonToArray");

        HashMap<String, ArrayList<String>> response = new Gson().fromJson(jsonInput,
                new TypeToken<HashMap<String, ArrayList<String>>>() {}.getType());

        ArrayList<ErrorModel> errorModelArrayList = new ArrayList<>();
        try {
            for (HashMap.Entry<String, ArrayList<String>> entry : response.entrySet()) {
                ArrayList<String> errors = entry.getValue();
                for (String errorStr : errors) {
                    ErrorModel errorModel = new ErrorModel();
                    errorModel.error=errorStr;
                    errorModelArrayList.add(errorModel);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return errorModelArrayList;
    }

}
