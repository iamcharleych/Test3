package com.chaplin.test3.domain.model;

import java.util.List;

public class PricingOption {

    transient private long mId;
    transient private String mItineraryOutboundLegId;
    transient private String mItineraryInboundLegId;
    transient private long mAgentId;
    private float mPrice;
    private List<Long> mAgents;

    public long getId() {
        return mId;
    }

    public long getAgentId() {
        return mAgentId;
    }

    public float getPrice() {
        return mPrice;
    }

    public List<Long> getAgents() {
        return mAgents;
    }

    public String getItineraryOutboundLegId() {
        return mItineraryOutboundLegId;
    }

    public String getItineraryInboundLegId() {
        return mItineraryInboundLegId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public void setPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    public void setAgents(List<Long> mAgents) {
        this.mAgents = mAgents;
    }

    public void setAgentId(long mAgentId) {
        this.mAgentId = mAgentId;
    }

    public void setItineraryOutboundLegId(String mItineraryOutboundLegId) {
        this.mItineraryOutboundLegId = mItineraryOutboundLegId;
    }

    public void setItineraryInboundLegId(String mItineraryInboundLegId) {
        this.mItineraryInboundLegId = mItineraryInboundLegId;
    }
}
