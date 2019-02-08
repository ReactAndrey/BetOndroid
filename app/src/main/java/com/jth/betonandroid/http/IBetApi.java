package com.jth.betonandroid.http;

import com.jth.betonandroid.entities.EventTypeResult;
import com.jth.betonandroid.model.BetfairServerResponse;

import java.util.List;

public interface IBetApi<T> {
    public void onResponse(int code);
    //public void onResponseEventTypeRes(int code, BetfairServerResponse<List<EventTypeResult>> bt);
    public void onResponseEventTypeRes(int code, BetfairServerResponse<T> bt);
}
