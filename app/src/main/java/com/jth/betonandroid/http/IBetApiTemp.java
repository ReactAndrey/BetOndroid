package com.jth.betonandroid.http;

import com.jth.betonandroid.entities.EventTypeResult;
import com.jth.betonandroid.model.BetfairServerResponse;

import java.util.List;

public interface IBetApiTemp<T> {
    public void onGetResponse(int code, BetfairServerResponse<List<EventTypeResult>> bt);
}
