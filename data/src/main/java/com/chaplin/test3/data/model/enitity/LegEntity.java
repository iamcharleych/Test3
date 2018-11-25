package com.chaplin.test3.data.model.enitity;

import androidx.annotation.NonNull;
import androidx.room.*;
import com.chaplin.test3.data.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "legs", foreignKeys = {
        @ForeignKey(entity = PlaceEntity.class, parentColumns = "id", childColumns = "originalStation"),
        @ForeignKey(entity = PlaceEntity.class, parentColumns = "id", childColumns = "destinationStation"),
        @ForeignKey(entity = CarrierEntity.class, parentColumns = "id", childColumns = "carrierId")
}, indices = {@Index("originalStation"), @Index("destinationStation"), @Index("carrierId")})
public class LegEntity {

    @SerializedName("Id")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String mId = Constants.EMPTY;
    @SerializedName("OriginStation")
    @NonNull
    @ColumnInfo(name = "originalStation")
    private String mOriginalStation = Constants.EMPTY;
    @SerializedName("DestinationStation")
    @NonNull
    @ColumnInfo(name = "destinationStation")
    private String mDestinationStation = Constants.EMPTY;
    @SerializedName("Departure")
    @NonNull
    @ColumnInfo(name = "departureDateTime")
    private String mDepartureDateTime = Constants.EMPTY;
    @SerializedName("Arrival")
    @NonNull
    @ColumnInfo(name = "arrivalDateTime")
    private String mArrivalDateTime = Constants.EMPTY;
    @ColumnInfo(name = "carrierId")
    transient private long mCarrierId;
    @ColumnInfo(name = "operatingCarrierId")
    transient private long mOperatingCarrierId;
    @SerializedName("Duration")
    @ColumnInfo(name = "duration")
    private long mDuration;
    @SerializedName("JourneyMode")
    @NonNull
    @ColumnInfo(name = "journeyMode")
    private String mJourneyMode = Constants.EMPTY;
    @SerializedName("Directionality")
    @NonNull
    @ColumnInfo(name = "directionality")
    private String mDirectionality = Constants.EMPTY;
    // db ignored
    @SerializedName("Carriers")
    @Ignore
    private List<Long> mCarriersIds;
    @SerializedName("OperatingCarriers")
    @Ignore
    private List<Long> mOperatingCarriersIds;
    @SerializedName("SegmentIds")
    @Ignore
    private List<Long> mSegmentIds;
    @SerializedName("FlightNumbers")
    @Ignore
    private List<FlightNumberEntity> mFlightNumbers;

    @Ignore
    public LegEntity() {}

    public LegEntity(@NonNull String mId,
                     @NonNull String mOriginalStation,
                     @NonNull String mDestinationStation,
                     @NonNull String mDepartureDateTime,
                     @NonNull String mArrivalDateTime,
                     long mCarrierId,
                     long mOperatingCarrierId,
                     long mDuration,
                     @NonNull String mJourneyMode,
                     @NonNull String mDirectionality) {
        this.mId = mId;
        this.mOriginalStation = mOriginalStation;
        this.mDestinationStation = mDestinationStation;
        this.mDepartureDateTime = mDepartureDateTime;
        this.mArrivalDateTime = mArrivalDateTime;
        this.mCarrierId = mCarrierId;
        this.mOperatingCarrierId = mOperatingCarrierId;
        this.mDuration = mDuration;
        this.mJourneyMode = mJourneyMode;
        this.mDirectionality = mDirectionality;
    }

    public String getId() {
        return mId;
    }

    @NonNull
    public String getOriginalStation() {
        return mOriginalStation;
    }

    @NonNull
    public String getDestinationStation() {
        return mDestinationStation;
    }

    @NonNull
    public String getDepartureDateTime() {
        return mDepartureDateTime;
    }

    @NonNull
    public String getArrivalDateTime() {
        return mArrivalDateTime;
    }

    @NonNull
    public long getCarrierId() {
        return mCarrierId;
    }

    @NonNull
    public long getOperatingCarrierId() {
        return mOperatingCarrierId;
    }

    public long getDuration() {
        return mDuration;
    }

    @NonNull
    public String getJourneyMode() {
        return mJourneyMode;
    }

    @NonNull
    public String getDirectionality() {
        return mDirectionality;
    }

    public List<Long> getCarriersIds() {
        return mCarriersIds;
    }

    public List<Long> getOperatingCarriersIds() {
        return mOperatingCarriersIds;
    }

    public List<Long> getSegmentIds() {
        return mSegmentIds;
    }

    public List<FlightNumberEntity> getFlightNumbers() {
        return mFlightNumbers;
    }

    public void setId(@NonNull String mId) {
        this.mId = mId;
    }

    public void setOriginalStation(@NonNull String mOriginalStation) {
        this.mOriginalStation = mOriginalStation;
    }

    public void setDestinationStation(@NonNull String mDestinationStation) {
        this.mDestinationStation = mDestinationStation;
    }

    public void setDepartureDateTime(@NonNull String mDepartureDateTime) {
        this.mDepartureDateTime = mDepartureDateTime;
    }

    public void setArrivalDateTime(@NonNull String mArrivalDateTime) {
        this.mArrivalDateTime = mArrivalDateTime;
    }

    public void setCarrierId(long mCarrierId) {
        this.mCarrierId = mCarrierId;
    }

    public void setOperatingCarrierId(long mOperatingCarrierId) {
        this.mOperatingCarrierId = mOperatingCarrierId;
    }

    public void setDuration(long mDuration) {
        this.mDuration = mDuration;
    }

    public void setJourneyMode(@NonNull String mJourneyMode) {
        this.mJourneyMode = mJourneyMode;
    }

    public void setDirectionality(@NonNull String mDirectionality) {
        this.mDirectionality = mDirectionality;
    }

    public void setCarriersIds(List<Long> mCarriersIds) {
        this.mCarriersIds = mCarriersIds;
    }

    public void setOperatingCarriersIds(List<Long> mOperatingCarriersIds) {
        this.mOperatingCarriersIds = mOperatingCarriersIds;
    }

    public void setSegmentIds(List<Long> mSegmentIds) {
        this.mSegmentIds = mSegmentIds;
    }

    public void setFlightNumbers(List<FlightNumberEntity> mFlightNumbers) {
        this.mFlightNumbers = mFlightNumbers;
    }
}
