package com.chaplin.test3.data.network.core;

public abstract class RestClient<RESPONSE> {

    public abstract RESPONSE execute(DataRequest request);
}
