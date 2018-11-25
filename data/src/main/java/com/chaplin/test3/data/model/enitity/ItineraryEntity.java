package com.chaplin.test3.data.model.enitity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import com.chaplin.test3.data.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "itineraries",
        primaryKeys = {"outboundLegId", "inboundLegId"})
public class ItineraryEntity {

    @SerializedName("OutboundLegId")
    @NonNull
    @ColumnInfo(name = "outboundLegId")
    private String mOutboundLegId = Constants.EMPTY;
    @SerializedName("InboundLegId")
    @NonNull
    @ColumnInfo(name = "inboundLegId")
    private String mInboundLegId = Constants.EMPTY;
    // db ignored
    @SerializedName("PricingOptions")
    @Ignore
    private List<PricingOptionEntity> mPricingOptions;

    @Ignore
    public ItineraryEntity() {}

    public ItineraryEntity(@NonNull String mOutboundLegId, @NonNull String mInboundLegId) {
        this.mOutboundLegId = mOutboundLegId;
        this.mInboundLegId = mInboundLegId;
    }

    public String getOutboundLegId() {
        return mOutboundLegId;
    }

    public String getInboundLegId() {
        return mInboundLegId;
    }

    public List<PricingOptionEntity> getPricingOptions() {
        return mPricingOptions;
    }

    public void setOutboundLegId(@NonNull String mOutboundLegId) {
        this.mOutboundLegId = mOutboundLegId;
    }

    public void setInboundLegId(@NonNull String mInboundLegId) {
        this.mInboundLegId = mInboundLegId;
    }

    public void setPricingOptions(List<PricingOptionEntity> mPricingOptions) {
        this.mPricingOptions = mPricingOptions;
    }
}
