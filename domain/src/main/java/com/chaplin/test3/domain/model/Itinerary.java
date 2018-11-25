package com.chaplin.test3.domain.model;

import java.util.List;

public class Itinerary {

    private String mOutboundLegId;
    private String mInboundLegId;
    private List<PricingOption> mPricingOptions;

    public String getOutboundLegId() {
        return mOutboundLegId;
    }

    public void setOutboundLegId(String outboundLegId) {
        mOutboundLegId = outboundLegId;
    }

    public String getInboundLegId() {
        return mInboundLegId;
    }

    public void setInboundLegId(String inboundLegId) {
        mInboundLegId = inboundLegId;
    }

    public List<PricingOption> getPricingOptions() {
        return mPricingOptions;
    }

    public void setPricingOptions(List<PricingOption> pricingOptions) {
        mPricingOptions = pricingOptions;
    }
}
