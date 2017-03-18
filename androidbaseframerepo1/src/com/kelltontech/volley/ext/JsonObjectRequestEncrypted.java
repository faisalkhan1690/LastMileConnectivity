package com.kelltontech.volley.ext;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.kelltontech.application.BaseApplication;
import com.kelltontech.model.EncryptionModel;
import com.kelltontech.utils.EncryptionUtils;
import com.kelltontech.utils.StringUtils;

import java.util.Map;

/**
 * <h1><font color="orange">JsonObjectRequestEncrypted</font></h1>
 * Implementation class of GsonObjectRequest.
 * Use this class in case you want to send your data in the encrypted form. else use JsonObjectRequest class.
 *
 * @author Faisal Khan
 */


public class JsonObjectRequestEncrypted<T> extends GsonObjectRequest<T> {

    private IEncryption iJsonObjectRequestCustomCallBack;
    private Class<T> mClass;
    private Response.ErrorListener mErrorListener;
    public static boolean isEncryptionRequired = true; // set true if Encryption required

    public JsonObjectRequestEncrypted(String url, Map<String, String> mRequestHeaders, String jsonPayload, Class<T> clazz,
                                      Response.ErrorListener errorListener, IEncryption iJsonObjectRequestCustomCallBack) {
        super(url, mRequestHeaders, getEncryptedModel(jsonPayload), encryptionRequired(clazz), errorListener);
        this.iJsonObjectRequestCustomCallBack = iJsonObjectRequestCustomCallBack;
        this.mClass = clazz;
        this.mErrorListener = errorListener;
    }

    private static <T> Class<T> encryptionRequired(Class<T> classz) {
        if (isEncryptionRequired) {
            return (Class<T>) EncryptionModel.class;
        } else {
            return classz;
        }
    }

    public JsonObjectRequestEncrypted(int method, String url, Map<String, String> mRequestHeaders, String jsonPayload, Class<T> clazz, Response.ErrorListener errorListener, IEncryption iJsonObjectRequestCustomCallBack, Gson gson) {
        super(method, url, mRequestHeaders, getEncryptedModel(jsonPayload), encryptionRequired(clazz), errorListener, gson);
        this.iJsonObjectRequestCustomCallBack = iJsonObjectRequestCustomCallBack;
        this.mClass = clazz;
        this.mErrorListener = errorListener;
    }

    private static String getEncryptedModel(String jsonPayload) {
        if (isEncryptionRequired) {
            if (BaseApplication.IS_LOG_ENABLE) {
                if (jsonPayload != null) {
                    Log.e("RequestBodyEncrypted", jsonPayload);
                } else
                    Log.e("RequestBodyEncrypted", " mRequestBody is NULL");
            }
            return new Gson().toJson(new EncryptionModel(EncryptionUtils.encryptToBase64String(jsonPayload)));
        } else {
            return jsonPayload;
        }

    }

    @Override
    protected void deliverResponse(T response) {
        if (isEncryptionRequired) {
            String data;
            EncryptionModel encryptionModel;
            try{
                encryptionModel=(EncryptionModel) response;
                data = EncryptionUtils.decryptToBase64String((encryptionModel).str);
            }catch (NullPointerException | ClassCastException e){
                e.printStackTrace();
                if (mErrorListener != null)
                    mErrorListener.onErrorResponse(new VolleyError("Data is null after decryption"));
                return;
            }

            if (BaseApplication.IS_LOG_ENABLE) {
                if (data != null) {
                    Log.e("JsonResponseDecrypted", data);
                } else
                    Log.e("JsonResponseDecrypted", " mRequestBody is NULL");
            }
            if (StringUtils.isNullOrEmpty(data)) {
                if (mErrorListener != null)
                    mErrorListener.onErrorResponse(new CmVolleyError(encryptionModel.responseMessage,encryptionModel.status,encryptionModel.responseCode));
            } else {
                try {
                    if (iJsonObjectRequestCustomCallBack != null)
                        iJsonObjectRequestCustomCallBack.deliverResponseDecrypted(new Gson().fromJson(data, mClass));
                } catch (Exception e) {
                    if (mErrorListener != null)
                        mErrorListener.onErrorResponse(new CmVolleyError(encryptionModel.responseMessage,encryptionModel.status,encryptionModel.responseCode));
                }

            }
        } else {
            try {
                if (iJsonObjectRequestCustomCallBack != null)
                    iJsonObjectRequestCustomCallBack.deliverResponseDecrypted(response);
                else if (mErrorListener != null)
                    mErrorListener.onErrorResponse(new VolleyError("Data is null after decryption"));
            } catch (Exception e) {
                if (mErrorListener != null)
                    mErrorListener.onErrorResponse(new VolleyError("Data is null after decryption"));
            }

        }

    }
}
