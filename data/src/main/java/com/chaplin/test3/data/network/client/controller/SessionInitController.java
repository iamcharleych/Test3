package com.chaplin.test3.data.network.client.controller;

import com.chaplin.test3.data.network.client.API;
import com.chaplin.test3.data.network.client.ApiConstants;
import com.chaplin.test3.data.network.core.DataResponse;
import io.reactivex.Flowable;

import java.util.Map;

public class SessionInitController {

    public static Flowable<DataResponse> startSession(API api, Map<String, String> params) {
        return api.startSession(
                params.get(ApiConstants.FIELD_CABIN_CLASS),
                params.get(ApiConstants.FIELD_COUNTRY),
                params.get(ApiConstants.FIELD_CURRENCY),
                params.get(ApiConstants.FIELD_LOCALE),
                params.get(ApiConstants.FIELD_LOCATION_SCHEMA),
                params.get(ApiConstants.FIELD_ORIGIN_PLACE),
                params.get(ApiConstants.FIELD_DESTINATION_PLACE),
                params.get(ApiConstants.FIELD_OUTBOUND_DATE),
                params.get(ApiConstants.FIELD_INBOUND_DATE),
                params.get(ApiConstants.FIELD_ADULTS),
                params.get(ApiConstants.FIELD_CHILDREN),
                params.get(ApiConstants.FIELD_INFANTS),
                params.get(ApiConstants.FIELD_API_KEY))
                .flatMap(response -> {
                    final String pollingUrl = response.headers().get("Location");

                    final DataResponse<String> dataResponse = new DataResponse<>();
                    dataResponse.code = response.code();
                    dataResponse.responseObject = pollingUrl;
                    return Flowable.just(dataResponse);
                });
    }
}
