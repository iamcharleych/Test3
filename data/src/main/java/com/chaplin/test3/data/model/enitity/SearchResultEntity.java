package com.chaplin.test3.data.model.enitity;

import androidx.room.ColumnInfo;
import com.chaplin.test3.domain.model.SearchResult;

public class SearchResultEntity implements SearchResult {

    @ColumnInfo(name = "outboundLegId")
    private String mOutboundLegId;
    @ColumnInfo(name = "inboundLegId")
    private String mInboundLegId;
    @ColumnInfo(name = "price")
    private float mPrice;
    @ColumnInfo(name = "agentName")
    private String mAgentName;

    @ColumnInfo(name = "outDepartureDateTime")
    private String mOutboundingDepartureDateTime;
    @ColumnInfo(name = "outArrivalDateTime")
    private String mOutboundingArrivalDateTime;
    @ColumnInfo(name = "outCarrierName")
    private String mOutboundingCarrierName;
    @ColumnInfo(name = "outOriginalPlaceCode")
    private String mOutboundingOriginalPlaceCode;
    @ColumnInfo(name = "outDestinationPlaceCode")
    private String mOutboundingDestinationPlaceCode;
    @ColumnInfo(name = "outDuration")
    private long mOutboundingDuration;
    @ColumnInfo(name = "outSegmentsCount")
    private int mOutboundingSegmentsCount;

    @ColumnInfo(name = "inDepartureDateTime")
    private String mInboundingDepartureDateTime;
    @ColumnInfo(name = "inArrivalDateTime")
    private String mInboundingArrivalDateTime;
    @ColumnInfo(name = "inCarrierName")
    private String mInboundingCarrierName;
    @ColumnInfo(name = "inOriginalPlaceCode")
    private String mInboundingOriginalPlaceCode;
    @ColumnInfo(name = "inDestinationPlaceCode")
    private String mInboundingDestinationPlaceCode;
    @ColumnInfo(name = "inDuration")
    private long mInboundingDuration;
    @ColumnInfo(name = "inSegmentsCount")
    private int mInboundingSegmentsCount;

    public SearchResultEntity(String mOutboundLegId, String mInboundLegId,
                              float mPrice, String mAgentName, String mOutboundingDepartureDateTime,
                              String mOutboundingArrivalDateTime, String mOutboundingCarrierName,
                              String mOutboundingOriginalPlaceCode, String mOutboundingDestinationPlaceCode,
                              long mOutboundingDuration, int mOutboundingSegmentsCount,
                              String mInboundingDepartureDateTime, String mInboundingArrivalDateTime,
                              String mInboundingCarrierName, String mInboundingOriginalPlaceCode,
                              String mInboundingDestinationPlaceCode, long mInboundingDuration,
                              int mInboundingSegmentsCount) {
        this.mOutboundLegId = mOutboundLegId;
        this.mInboundLegId = mInboundLegId;
        this.mPrice = mPrice;
        this.mAgentName = mAgentName;
        this.mOutboundingDepartureDateTime = mOutboundingDepartureDateTime;
        this.mOutboundingArrivalDateTime = mOutboundingArrivalDateTime;
        this.mOutboundingCarrierName = mOutboundingCarrierName;
        this.mOutboundingOriginalPlaceCode = mOutboundingOriginalPlaceCode;
        this.mOutboundingDestinationPlaceCode = mOutboundingDestinationPlaceCode;
        this.mOutboundingDuration = mOutboundingDuration;
        this.mOutboundingSegmentsCount = mOutboundingSegmentsCount;
        this.mInboundingDepartureDateTime = mInboundingDepartureDateTime;
        this.mInboundingArrivalDateTime = mInboundingArrivalDateTime;
        this.mInboundingCarrierName = mInboundingCarrierName;
        this.mInboundingOriginalPlaceCode = mInboundingOriginalPlaceCode;
        this.mInboundingDestinationPlaceCode = mInboundingDestinationPlaceCode;
        this.mInboundingDuration = mInboundingDuration;
        this.mInboundingSegmentsCount = mInboundingSegmentsCount;
    }

    // region Getters required by Room

    public float getPrice() {
        return mPrice;
    }

    public String getAgentName() {
        return mAgentName;
    }

    public String getOutboundingDepartureDateTime() {
        return mOutboundingDepartureDateTime;
    }

    public String getOutboundingArrivalDateTime() {
        return mOutboundingArrivalDateTime;
    }

    public String getOutboundingCarrierName() {
        return mOutboundingCarrierName;
    }

    public String getOutboundingOriginalPlaceCode() {
        return mOutboundingOriginalPlaceCode;
    }

    public String getOutboundingDestinationPlaceCode() {
        return mOutboundingDestinationPlaceCode;
    }

    public long getOutboundingDuration() {
        return mOutboundingDuration;
    }

    public int getOutboundingSegmentsCount() {
        return mOutboundingSegmentsCount;
    }

    public String getInboundingDepartureDateTime() {
        return mInboundingDepartureDateTime;
    }

    public String getInboundingArrivalDateTime() {
        return mInboundingArrivalDateTime;
    }

    public String getInboundingCarrierName() {
        return mInboundingCarrierName;
    }

    public String getInboundingOriginalPlaceCode() {
        return mInboundingOriginalPlaceCode;
    }

    public String getInboundingDestinationPlaceCode() {
        return mInboundingDestinationPlaceCode;
    }

    public long getInboundingDuration() {
        return mInboundingDuration;
    }

    public int getInboundingSegmentsCount() {
        return mInboundingSegmentsCount;
    }

    public String getOutboundLegId() {
        return mOutboundLegId;
    }

    public String getInboundLegId() {
        return mInboundLegId;
    }

    // endregion
}
