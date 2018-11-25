package com.chaplin.test3.data.network.core;

public class DataResponse<T> {

    public transient DataRequest request;

    public int code;

    public T responseObject;

}
