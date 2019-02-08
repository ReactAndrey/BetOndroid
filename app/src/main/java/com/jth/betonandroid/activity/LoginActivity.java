package com.jth.betonandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jth.betonandroid.R;
import com.jth.betonandroid.doc.Globals;
import com.jth.betonandroid.entities.*;
import com.jth.betonandroid.enums.Exchange;
import com.jth.betonandroid.http.BetAPIConstant;
import com.jth.betonandroid.http.BetApi;
import com.jth.betonandroid.http.IBetApi;
import com.jth.betonandroid.http.IBetApiTemp;
import com.jth.betonandroid.http.VolleyCallback;
import com.jth.betonandroid.model.BetfairClient;
import com.jth.betonandroid.model.BetfairServerResponse;
import com.jth.betonandroid.model.MarketFilter;
import com.jth.betonandroid.model.Network;
import com.jth.betonandroid.model.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class LoginActivity extends AppCompatActivity implements IBetApi<List<EventTypeResult>>,View.OnClickListener {
    public final static String  LOGIN_TAG = "LOGINUSER";
    Button btnLogin,btnAllEvents;
    static int port = 443;
    String username = "sheppya68@gmail.com";
    String password = "Medic911";
    String appKey = "omnGANRkjjpBl76k";
    UserInfo userInfo;
    BetfairClient betfairClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    public void initView() {
        btnLogin = this.findViewById(R.id.btn_login);
        btnAllEvents = this.findViewById(R.id.btn_allevents);
        btnLogin.setOnClickListener(this);
        btnAllEvents.setOnClickListener(this);
    }
    public void actionLogin() {
        Date d = new Date();
        betfairClient = new BetfairClient(Exchange.UK,appKey);
        betfairClient.login(username,password,this);
        Calendar c = Calendar.getInstance();

        int seconds = c.get(Calendar.SECOND);
        int minutes = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR);
        String time = hour + ":" + minutes + ":" + seconds;


        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);
        //String date = day + "/" + month + "/" + 1year;

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        MarketFilter mFilter = new MarketFilter();
        Log.i("MarketFilter",mFilter.toString());

        //Log.i("dddd",date+ " "+time);
        //Log.i("dddd",date.d));
//        BetApi.loginUser(username, password, new VolleyCallback() {
//            @Override
//            public void onSuccess(String result) {
//                JSONObject jsonObj = null;
//                try {
//                    jsonObj = new JSONObject(result);
//                    betfairClient.sessionToken = jsonObj.getString(BetAPIConstant.ITEM_TOKEN);
//                    betfairClient.status = jsonObj.getString(BetAPIConstant.ITEM_STATUS);
//                    betfairClient.networkClient = new Network(appKey, betfairClient.sessionToken, false);
//                    if(betfairClient.status.equals("SUCCESS"))
//                        Log.i("success=","sucess");
//                    else
//                        Log.i("success=","fail");
//                } catch (JSONException e) {
//                    Log.i("success=","Can not connect");
//                    e.printStackTrace();
//                }
//            }
//            @Override
//            public void onError(Object error) {
//                Log.e(LOGIN_TAG, error.toString());
//            }        });
    }
    public void actionAllEvents()
    {

        Log.i("Allevents","start");
        MarketFilter mFilter = new MarketFilter();
        Log.i("MarketFilter",mFilter.toString());
        //BetfairServerResponse<List<EventTypeResult>> betfairServerResponse;
        //betfairServerResponse.getResponse().get(0).
        //betfairServerResponse =
        betfairClient.listEventTypes(mFilter,this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_login:
                actionLogin();
                break;
            case R.id.btn_allevents:
                actionAllEvents();
                break;
        }
    }
    @Override
    public void onResponse(int code) {

        switch(code)
        {
            case BetAPIConstant.VALUE_STATUS_SUCCESS:
                actionAllEvents();
                Log.i("success","succ");
                break;
            case BetAPIConstant.VALUE_STATUS_FAIL1:
                ///username or password wrong
                break;
            case BetAPIConstant.VALUE_STATUS_FAIL2:
                ///username or password wrong
                break;
            case BetAPIConstant.VALUE_STATUS_FAIL3:
                ///Sign In fail
                ///Internet Connection Problem
                break;

        }
    }
    @Override
    //public void onResponseEventTypeRes(int code,BetfairServerResponse<List<EventTypeResult>> bt) {
    public void onResponseEventTypeRes(int code,BetfairServerResponse<List<EventTypeResult>> bt) {
        switch(code)
        {
            case BetAPIConstant.VALUE_STATUS_SUCCESS:
                Log.i("fffaailll","aaaaaaa");
                //Log.i("AllEvnts=",String.valueOf(bt.getResponse().size()));

                //Log.i("AllEvnts=",String.valueOf(bt.getResponse().get(0).getEventType().getName()));
                        //bt.getResponse().get(0).());
                break;
            case BetAPIConstant.VALUE_STATUS_FAIL1:
                break;
            case BetAPIConstant.VALUE_STATUS_FAIL2:
                break;
            case BetAPIConstant.VALUE_STATUS_FAIL3:
                break;

        }
    }
}
