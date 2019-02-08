package com.jth.betonandroid.model;

import java.util.Map;

public class JsonRequestV {

    public String jsonrpc = "2.0";
    public String method;
    public int id;
    public Map<String, Object> params;

    public JsonRequestV() { }
}
