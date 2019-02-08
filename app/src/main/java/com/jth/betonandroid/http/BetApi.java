package com.jth.betonandroid.http;


import android.util.Log;

import java.util.HashMap;
public class BetApi {
    public static void loginUser(String userId, String password, final VolleyCallback resultCallback) {
        String url = BetAPIConstant.BASE_URL + BetAPIConstant.API_LOGIN;

        HashMap<String, String> data = new HashMap<>();
        data.put(BetAPIConstant.ITEM_USERID, userId);
        data.put(BetAPIConstant.ITEM_PASSWORD, password);
        VolleyRequest.getStringResponsePost(url, data, resultCallback);
    }
    public static void getAllEvents(String url,String requestData,HashMap headers,final VolleyCallback resultCallback)
    {

        VolleyRequest.getStringDataResponsePost(url, requestData,headers, resultCallback);
    }
}