package com.chaplin.test3.data.model.enitity;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.chaplin.test3.data.BuildConfig;
import com.chaplin.test3.data.network.client.ApiConstants;
import com.chaplin.test3.data.pref.Pref;

public class Session {

    private static final String EMPTY_BASE_POLLING_URL = "";

    @NonNull
    private String mBasePollingUrl;

    public Session(@NonNull String pollingUrl) {
        mBasePollingUrl = pollingUrl;
    }

    public Session() {
        this(EMPTY_BASE_POLLING_URL);
    }

    public void updateBasePollingUrl(@NonNull String basePollingUrl) {
        mBasePollingUrl = basePollingUrl;
        Pref.Session.BASE_POLLING_URL.set(basePollingUrl);
    }

    public String getBasePollingUrl() {
        return mBasePollingUrl;
    }

    public boolean hasBasePollingUrl() {
        return !TextUtils.isEmpty(mBasePollingUrl);
    }

    public String getPollingUrl(int pageIndex) {
        StringBuilder sb = new StringBuilder(mBasePollingUrl);

        sb.append("?");
        sb.append(ApiConstants.FIELD_API_KEY).append("=").append(BuildConfig.API_KEY).append("&");
        sb.append(ApiConstants.FIELD_PAGE_INDEX).append("=").append(pageIndex);

        return sb.toString();
    }

    public void resetBasePollingUrl() {
        mBasePollingUrl = EMPTY_BASE_POLLING_URL;
    }
}
