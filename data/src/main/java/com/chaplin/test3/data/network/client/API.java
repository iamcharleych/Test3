package com.chaplin.test3.data.network.client;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.*;

public interface API {

    @FormUrlEncoded
    @POST("pricing/v1.0")
    Flowable<Response<ResponseBody>> startSession(
            @Field(ApiConstants.FIELD_CABIN_CLASS) String cabinClass,
            @Field(ApiConstants.FIELD_COUNTRY) String country,
            @Field(ApiConstants.FIELD_CURRENCY) String currency,
            @Field(ApiConstants.FIELD_LOCALE) String locale,
            @Field(ApiConstants.FIELD_LOCATION_SCHEMA) String locationSchema,
            @Field(ApiConstants.FIELD_ORIGIN_PLACE) String originPlace,
            @Field(ApiConstants.FIELD_DESTINATION_PLACE) String destinationPlace,
            @Field(ApiConstants.FIELD_OUTBOUND_DATE) String outboundDate,
            @Field(ApiConstants.FIELD_INBOUND_DATE) String inboundDate,
            @Field(ApiConstants.FIELD_ADULTS) String adults,
            @Field(ApiConstants.FIELD_CHILDREN) String children,
            @Field(ApiConstants.FIELD_INFANTS) String infants,
            @Field(ApiConstants.FIELD_API_KEY) String apiKey
    );

    @GET
    Flowable<Response<ResponseBody>> pollSearchResults(@Url String pollingUrl);
}
