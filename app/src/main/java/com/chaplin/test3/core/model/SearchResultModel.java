package com.chaplin.test3.core.model;

public class SearchResultModel {

    private String mPrice;
    private String mAgentName;

    private String mOutboundingSummary;
    private String mOutboundingTiming;
    private String mOutboundingFlightType;
    private String mOutboundingDuration;

    private String mInboundingSummary;
    private String mInboundingTiming;
    private String mInboundingFlightType;
    private String mInboundingDuration;

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getAgentName() {
        return mAgentName;
    }

    public void setAgentName(String agentName) {
        mAgentName = agentName;
    }

    // region Outbounding

    public String getOutboundingSummary() {
        return mOutboundingSummary;
    }

    public void setOutboundingSummary(String outboundingSummary) {
        mOutboundingSummary = outboundingSummary;
    }

    public String getOutboundingTiming() {
        return mOutboundingTiming;
    }

    public void setOutboundingTiming(String outboundingTiming) {
        mOutboundingTiming = outboundingTiming;
    }

    public String getOutboundingFlightType() {
        return mOutboundingFlightType;
    }

    public void setOutboundingFlightType(String outboundingFlightType) {
        mOutboundingFlightType = outboundingFlightType;
    }

    public String getOutboundingDuration() {
        return mOutboundingDuration;
    }

    public void setOutboundingDuration(String outboundingDuration) {
        mOutboundingDuration = outboundingDuration;
    }

    // endregion

    // region Inbounding

    public String getInboundingSummary() {
        return mInboundingSummary;
    }

    public void setInboundingSummary(String inboundingSummary) {
        mInboundingSummary = inboundingSummary;
    }

    public String getInboundingTiming() {
        return mInboundingTiming;
    }

    public void setInboundingTiming(String inboundingTiming) {
        mInboundingTiming = inboundingTiming;
    }

    public String getInboundingFlightType() {
        return mInboundingFlightType;
    }

    public void setInboundingFlightType(String inboundingFlightType) {
        mInboundingFlightType = inboundingFlightType;
    }

    public String getInboundingDuration() {
        return mInboundingDuration;
    }

    public void setInboundingDuration(String inboundingDuration) {
        mInboundingDuration = inboundingDuration;
    }

    // endregion
}
