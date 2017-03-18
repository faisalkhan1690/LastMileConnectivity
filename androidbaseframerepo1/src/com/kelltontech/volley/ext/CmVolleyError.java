package com.kelltontech.volley.ext;

import com.android.volley.VolleyError;

/**
 * Custom volley error for club  mahindra
 */
public class CmVolleyError extends VolleyError {
    public int mStatus;
    public int mResponseCode;
    public String mResponseMessage;

    public CmVolleyError(String responseMessage,int status,int responseCode) {
        super(responseMessage);
        mResponseMessage=responseMessage;
        mStatus=status;
        mResponseCode = responseCode;
    }
}
