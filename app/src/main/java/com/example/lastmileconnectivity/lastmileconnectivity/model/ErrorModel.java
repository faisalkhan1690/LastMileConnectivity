package com.example.lastmileconnectivity.lastmileconnectivity.model;

import android.annotation.SuppressLint;

import com.kelltontech.model.BaseModel;

/**
 * <h1><font color="orange">ErrorModel</font></h1>
 * Model class Error from api
 *
 * @author Soumya
 */
@SuppressLint("ParcelCreator")
public class ErrorModel extends BaseModel {
    public String error;
    public String responseMessage;
    public int status;
    public int responseCode;

    public ErrorModel(){
        //default constructor
    }
    public ErrorModel(String error, int status, int responseCode){
        this.error=error;
        this.responseMessage=error;
        this.responseCode=responseCode;
        this.status=status;
    }

}
