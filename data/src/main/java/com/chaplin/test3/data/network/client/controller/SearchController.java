package com.chaplin.test3.data.network.client.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.chaplin.test3.data.model.enitity.*;
import com.chaplin.test3.data.network.client.API;
import com.chaplin.test3.data.network.core.DataResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchController {

    private static final String STATUS = "Status";
    private static final String ITINERARIES = "Itineraries";
    private static final String LEGS = "Legs";
    private static final String SEGMENTS = "Segments";
    private static final String CARRIERS = "Carriers";
    private static final String AGENTS = "Agents";
    private static final String PLACES = "Places";
    private static final String CURRENCIES = "Currencies";

    public static Flowable<DataResponse> search(API api, String pollingUrl) {
        return api.pollSearchResults(pollingUrl)
                .flatMap(response -> {
                    ResponseBody responseBody = response.body();

                    if (responseBody == null) {
                        return Flowable.error(new IllegalArgumentException("Poll response is empty"));
                    }

                    Parser parser = new Parser(responseBody.string());

                    final DataResponse<Parser> dataResponse = new DataResponse<>();
                    dataResponse.responseObject = parser;
                    return Flowable.just(dataResponse);
                });
    }

    public static class Parser {

        @NonNull
        private final JSONObject mJson;
        @NonNull
        private final Gson mGson;

        private List<ItineraryEntity> mItineraries;
        private List<LegEntity> mLegs;
        private List<SegmentEntity> mSegments;
        private List<CarrierEntity> mCarriers;
        private List<AgentEntity> mAgents;
        private List<PlaceEntity> mPlaces;
        private CurrencyEntity mCurrency;

        Parser(String source) throws JSONException {
            mJson = new JSONObject(source);
            mGson = new Gson();
        }

        public String parseStatus() {
            return mJson.optString(STATUS);
        }

        @NonNull
        public List<ItineraryEntity> parseItineraries() {
            if (mItineraries != null) {
                return mItineraries;
            }

            mItineraries = Collections.emptyList();

            if (!mJson.has(ITINERARIES)) {
                return mItineraries;
            }

            try {
                final String strJsonArray = mJson.getJSONArray(ITINERARIES).toString();
                mItineraries = mGson.fromJson(strJsonArray, new TypeToken<ArrayList<ItineraryEntity>>() {}.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return mItineraries;
        }

        @NonNull
        public List<LegEntity> parseLegs() {
            if (mLegs != null) {
                return mLegs;
            }

            mLegs = Collections.emptyList();

            if (!mJson.has(LEGS)) {
                return mLegs;
            }

            try {
                final String strJsonArray = mJson.getJSONArray(LEGS).toString();
                mLegs = mGson.fromJson(strJsonArray, new TypeToken<ArrayList<LegEntity>>() {}.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return mLegs;
        }

        @NonNull
        public List<SegmentEntity> parseSegments() {
            if (mSegments != null) {
                return mSegments;
            }

            mSegments = Collections.emptyList();

            if (!mJson.has(SEGMENTS)) {
                return mSegments;
            }

            try {
                final String strJsonArray = mJson.getJSONArray(SEGMENTS).toString();
                mSegments = mGson.fromJson(strJsonArray, new TypeToken<ArrayList<SegmentEntity>>() {}.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return mSegments;
        }

        @NonNull
        public List<CarrierEntity> parseCarriers() {
            if (mCarriers != null) {
                return mCarriers;
            }

            mCarriers = Collections.emptyList();

            if (!mJson.has(CARRIERS)) {
                return mCarriers;
            }

            try {
                final String strJsonArray = mJson.getJSONArray(CARRIERS).toString();
                mCarriers = mGson.fromJson(strJsonArray, new TypeToken<ArrayList<CarrierEntity>>() {}.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return mCarriers;
        }

        @NonNull
        public List<AgentEntity> parseAgents() {
            if (mAgents != null) {
                return mAgents;
            }

            mAgents = Collections.emptyList();

            if (!mJson.has(AGENTS)) {
                return mAgents;
            }

            try {
                final String strJsonArray = mJson.getJSONArray(AGENTS).toString();
                mAgents = mGson.fromJson(strJsonArray, new TypeToken<ArrayList<AgentEntity>>() {}.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return mAgents;
        }

        @NonNull
        public List<PlaceEntity> parsePlaces() {
            if (mPlaces != null) {
                return mPlaces;
            }

            mPlaces = Collections.emptyList();

            if (!mJson.has(PLACES)) {
                return mPlaces;
            }

            try {
                final String strJsonArray = mJson.getJSONArray(PLACES).toString();
                mPlaces = mGson.fromJson(strJsonArray, new TypeToken<ArrayList<PlaceEntity>>() {}.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return mPlaces;
        }

        @Nullable
        public CurrencyEntity parseCurrency() {
            if (mCurrency != null) {
                return mCurrency;
            }

            CurrencyEntity currency = null;
            if (!mJson.has(CURRENCIES)) {
                return null;
            }

            try {
                final String strJsonArray = mJson.getJSONArray(CURRENCIES).toString();
                List<CurrencyEntity> result = mGson.fromJson(strJsonArray,  new TypeToken<ArrayList<CurrencyEntity>>() {}.getType());
                currency = result.get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

            mCurrency = currency;
            return currency;
        }
    }
}
