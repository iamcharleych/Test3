package com.chaplin.test3.data.network.client;

import com.chaplin.test3.data.BuildConfig;
import com.chaplin.test3.data.Constants;
import com.chaplin.test3.data.network.core.DataRequest;

public class Requests {

    /**
     *  available api operations
     *
     *  Please, for new operations use format: OP_(GET|POST|PUT|...)_<Operation name>
     */

    public static final String OP_POST_START_SESSION = "op_post_startSession";
    public static DataRequest startSession() {
        return new DataRequest(OP_POST_START_SESSION)
                .with(ApiConstants.FIELD_ADULTS, String.valueOf(Constants.ADULTS_COUNT))
                .with(ApiConstants.FIELD_API_KEY, BuildConfig.API_KEY)
                .with(ApiConstants.FIELD_CABIN_CLASS, Constants.CABIN_CLASS)
                .with(ApiConstants.FIELD_CHILDREN, String.valueOf(Constants.CHILDREN_COUNT))
                .with(ApiConstants.FIELD_COUNTRY, "UK")
                .with(ApiConstants.FIELD_CURRENCY, "GBP")
                .with(ApiConstants.FIELD_DESTINATION_PLACE, "LOND-sky")
                .with(ApiConstants.FIELD_ORIGIN_PLACE, "EDI-sky")
                .with(ApiConstants.FIELD_INFANTS, String.valueOf(Constants.INFANTS_COUNT))
                .with(ApiConstants.FIELD_LOCALE, "en-GB")
                .with(ApiConstants.FIELD_LOCATION_SCHEMA, "sky")
                .with(ApiConstants.FIELD_INBOUND_DATE, "2018-12-06")
                .with(ApiConstants.FIELD_OUTBOUND_DATE, "2018-12-05");
    }

    public static final String OP_GET_POLL = "op_get_poll";
    public static DataRequest poll(String pollingUrl) {
        return new DataRequest(OP_GET_POLL)
                .with(ApiConstants.PARAM_POLLING_URL, pollingUrl);
    }
}
