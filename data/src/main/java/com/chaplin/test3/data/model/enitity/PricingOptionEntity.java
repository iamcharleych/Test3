package com.chaplin.test3.data.model.enitity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.chaplin.test3.data.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@Entity(tableName = "pricing_options")
public class PricingOptionEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    transient private long mId;
    @NonNull
    @ColumnInfo(name = "outboundLegId")
    transient private String mItineraryOutboundLegId = Constants.EMPTY;
    @NonNull
    @ColumnInfo(name = "inboundLegId")
    transient private String mItineraryInboundLegId = Constants.EMPTY;
    @ColumnInfo(name = "agentId")
    transient private long mAgentId;
    @SerializedName("Price")
    @ColumnInfo(name = "price")
    private float mPrice;
    // db ignored
    @SerializedName("Agents")
    @Ignore
    private List<Long> mAgents;

    @Ignore
    public PricingOptionEntity() {}

    public PricingOptionEntity(long mId, @NonNull String itineraryOutboundLegId, @NonNull String itineraryInboundLegId,
                               long mAgentId, float mPrice) {
        this.mId = mId;
        this.mItineraryInboundLegId = itineraryInboundLegId;
        this.mItineraryOutboundLegId = itineraryOutboundLegId;
        this.mAgentId = mAgentId;
        this.mPrice = mPrice;
    }

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

    @NonNull
    public String getItineraryOutboundLegId() {
        return mItineraryOutboundLegId;
    }

    @NonNull
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

    public void setItineraryOutboundLegId(@NonNull String mItineraryOutboundLegId) {
        this.mItineraryOutboundLegId = mItineraryOutboundLegId;
    }

    public void setItineraryInboundLegId(@NonNull String mItineraryInboundLegId) {
        this.mItineraryInboundLegId = mItineraryInboundLegId;
    }
}
