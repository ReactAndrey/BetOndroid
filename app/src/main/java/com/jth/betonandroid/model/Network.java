package com.jth.betonandroid.model;
import android.util.Log;



import  com.google.common.reflect.TypeParameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jth.betonandroid.enums.Endpoint;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import com.google.common.reflect.TypeToken;
import com.jth.betonandroid.enums.Exchange;
import com.jth.betonandroid.http.BetAPIConstant;
import com.jth.betonandroid.http.BetApi;
import com.jth.betonandroid.http.IBetApi;
import com.jth.betonandroid.http.VolleyCallback;


public class Network{
        private String appKey;
        private String sessionToken;
        private Boolean gzipCompress;
        private String result="";
        long requestStartMillis;
        Gson gson;
        DateTime requestStart;
        public Network(
                String appKey,
                String sessionToken,
                Boolean gzipCompress) {
            this.appKey = appKey;
            this.sessionToken = sessionToken;
            this.gzipCompress = gzipCompress;
        }

        public <T> BetfairServerResponse<T> Invoke(
                final TypeToken<T> elementClass,
                Exchange exchange,
                Endpoint endpoint,
                String method,
                Map<String, Object> args,final IBetApi caller) {

            if (Helpers.isNullOrWhitespace(method)) throw new IllegalArgumentException(method);
             Log.d( formatEndpoint(endpoint), method);
            requestStart = DateTime.now();
            requestStartMillis = System.currentTimeMillis();

            String url;
            if (exchange == Exchange.AUS)
                url = "https://api-au.betfair.com/exchange";
            else
                url = "https://api.betfair.com/exchange";

            if (endpoint == Endpoint.Betting)
                url += "/betting/json-rpc/v1";
            else
                url += "/account/json-rpc/v1";

            JsonRequestV call = new JsonRequestV();
            call.method = method;
            call.params = args;
            call.id = 1;

            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

            String requestData = gson.toJson(call);
            Log.i("aa1",url);
            Log.i("aa1",requestData);
            HashMap headers = new HashMap();
            headers.put("Accept", "application/json");
            headers.put("X-Application", appKey);
            headers.put("X-Authentication", sessionToken);
            BetApi.getAllEvents(url, requestData,headers, new VolleyCallback() {
                @Override
                public void onSuccess(String res) {
                    JSONObject jsonObj = null;
                    try {
                        jsonObj = new JSONObject(res);
                        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
                        result = res;
                        Type underlyingType = new TypeToken<JsonResponse<T>>() {}.where(new TypeParameter<T>() {}, elementClass).getType();

                        JsonResponse<T> entity = gson.fromJson(result, underlyingType);
                        // should be returning Observable<Betfair...> here.

                        if (entity != null) {
                            BetfairServerResponse<T> response = new BetfairServerResponse<T>(
                                    entity.result,
                                    DateTime.now(),
                                    requestStart,
                                    (System.currentTimeMillis() - requestStartMillis) / 1000,
                                    entity.hasError);
                            Log.i("aa1",result);
                            caller.onResponseEventTypeRes(BetAPIConstant.VALUE_STATUS_SUCCESS,response);
                            //return response;
                        } else {
                            caller.onResponseEventTypeRes(BetAPIConstant.VALUE_STATUS_FAIL1,null);
//                            return new BetfairServerResponse<>(
//                                    null,
//                                    DateTime.now(),
//                                    requestStart,
//                                    (System.currentTimeMillis() - requestStartMillis) / 1000,
//                                    true);
                        }


                    } catch (JSONException e) {
                        caller.onResponseEventTypeRes(BetAPIConstant.VALUE_STATUS_FAIL2,null);
                        e.printStackTrace();
                    }
                    Log.i("Status=","success");
                }
                @Override
                public void onError(Object error) {
                    Log.e("error", error.toString());
                    caller.onResponseEventTypeRes(BetAPIConstant.VALUE_STATUS_FAIL3,null);
                }
            });
//                String result = requestSync(
//                        url,
//                        requestData,
//                        ContentType.APPLICATION_JSON,
//                        "application/json",
//                        appKey,
//                        sessionToken);

            return null;
        }

        public BetfairServerResponse<KeepAliveResponse> keepAliveSynchronous() {
            DateTime requestStart = DateTime.now();
            long requestStartMillis = System.currentTimeMillis();

            String keepAliveResponse="";
//            = this.requestSync(
//                    "https://identitysso.betfair.com/api/keepAlive",
//                    "",
//                    ContentType.APPLICATION_FORM_URLENCODED,
//                    "application/json",
//                    this.appKey,
//                    this.sessionToken);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
            Type typeToken = new TypeToken<KeepAliveResponse>() {
            }.getType();
            KeepAliveResponse entity = gson.fromJson(keepAliveResponse, typeToken);
            if (entity != null) {
                BetfairServerResponse<KeepAliveResponse> response = new BetfairServerResponse<>(
                        entity,
                        DateTime.now(),
                        requestStart,
                        (System.currentTimeMillis() - requestStartMillis) / 1000,
                        Boolean.parseBoolean(entity.error));
                return response;
            } else {
                KeepAliveResponse response = new KeepAliveResponse();
                response.error = "Keep Alive request failed.";
                return new BetfairServerResponse<>(
                        response,
                        DateTime.now(),
                        requestStart,
                        (System.currentTimeMillis() - requestStartMillis) / 1000,
                        true);
            }
        }

//        private String requestSync(
//                String url,
//                String requestPostData,
//                ContentType contentType,
//                String acceptType,
//                String appKey,
//                String sessionToken) {
//            Header[] headers = {
//                    new BasicHeader("X-Application", appKey),
//                    new BasicHeader("X-Authentication", sessionToken),
//                    new BasicHeader("Cache-Control", "no-cache"),
//                    new BasicHeader("Pragma", "no-cache"),
//                    new BasicHeader("Accept", acceptType)
//            };
//            SSLContext sslContext = SSLContexts.createSystemDefault();
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
//
//            CloseableHttpClient client = HttpClients.custom()
//                    .setDefaultHeaders(new ArrayList<>(Arrays.asList(headers)))
//                    .build();
//            try {
//                StringEntity entity = new StringEntity(requestPostData);
//                entity.setContentType(contentType.toString());
//                HttpPost post = new HttpPost(url);
//
//                post.setEntity(entity);
//
//                HttpResponse response = client.execute(post);
//                String json = EntityUtils.toString(response.getEntity(), "UTF-8");
//
//                return json;
//            } catch (IOException exception) {Log.i("aaa2","aa1");
//                return null;
//            }
//
//        }

        private String formatEndpoint(Endpoint endpoint) {
            return endpoint == Endpoint.Betting ? "betting" : "account";
        }
}

