package com.chaplin.test3.data.network.core;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class DataRequest {

    public static final String NO_OPERATION = "no_op";

    private final String mOperation;
    private int mStatus;
    private HashMap<String, String> mParams = new HashMap<>();

    public DataRequest(String operation) {
        mOperation = operation;
    }

    public DataRequest(DataRequest request) {
        this.mOperation = request.mOperation;
        this.mStatus = request.mStatus;
        this.mParams.putAll(request.mParams);
    }

    public DataRequest with(@NonNull String key, @NonNull String value) {
        mParams.put(key, value);

        return this;
    }

    public String getOperation() {
        return mOperation;
    }

    public int getStatus() {
        return mStatus;
    }

    public HashMap<String, String> getParams() {
        return mParams;
    }

    @Override
    public String toString() {
        return "DataRequest{" +
                ", operation='" + mOperation + '\'' +
                ", mParams=" + mParams +
                '}';
    }
}
