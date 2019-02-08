package com.jth.betonandroid.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class VolleyRequest {
    public static final String REQUEST_TAG = "com.jth.betonandroid";

    public static void  getStringResponse(String url, final VolleyCallback resultCallback){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(resultCallback != null){
                            resultCallback.onSuccess(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(resultCallback != null){
                    resultCallback.onError(error);
                }
            }
        });

        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        // Adding String request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, REQUEST_TAG);
    }

    public static void  getStringResponsePost(String url, final HashMap<String, String> data, final VolleyCallback resultCallback){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(resultCallback != null){
                            resultCallback.onSuccess(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(resultCallback != null){
                    resultCallback.onError(error);
                }
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            public Map getHeaders() throws AuthFailureError{
                HashMap headers = new HashMap();
                headers.put("Accept", "application/json");
                headers.put("X-Application", "omnGANRkjjpBl76k");

                return headers;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return data;
            }

        };
        //Log.i("dddd",stringRequest);
        // Adding String request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, REQUEST_TAG);
    }

    public static void  getStringDataResponsePost(String url,final String data,final HashMap headers, final VolleyCallback resultCallback){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(resultCallback != null){
                            resultCallback.onSuccess(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(resultCallback != null){
                    resultCallback.onError(error);
                }
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            public Map getHeaders() throws AuthFailureError{
               // HashMap headers = new HashMap();
                return headers;
            }
            @Override
            public byte[] getBody() throws AuthFailureError{
                return data.getBytes();
            }
        };

        // Adding String request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, REQUEST_TAG);
    }
}