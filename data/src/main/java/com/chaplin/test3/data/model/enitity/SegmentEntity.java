package com.chaplin.test3.data.model.enitity;

import androidx.annotation.NonNull;
import androidx.room.*;
import com.chaplin.test3.data.Constants;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "segments", foreignKeys = {
        @ForeignKey(entity = PlaceEntity.class, parentColumns = "id", childColumns = "originalStation"),
        @ForeignKey(entity = PlaceEntity.class, parentColumns = "id", childColumns = "destinationStation"),
        @ForeignKey(entity = CarrierEntity.class, parentColumns = "id", childColumns = "carrierId")
}, indices = {@Index("originalStation"), @Index("destinationStation"), @Index("carrierId")})
public class SegmentEntity {

    @SerializedName("Id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    private long mId;
    @SerializedName("OriginStation")
    @NonNull
    @ColumnInfo(name = "originalStation")
    private String mOriginalStation = Constants.EMPTY;
    @SerializedName("DestinationStation")
    @NonNull
    @ColumnInfo(name = "destinationStation")
    private String mDestinationStation = Constants.EMPTY;
    @SerializedName("DepartureDateTime")
    @NonNull
    @ColumnInfo(name = "departureDateTime")
    private String mDepartureDateTime = Constants.EMPTY;
    @SerializedName("ArrivalDateTime")
    @NonNull
    @ColumnInfo(name = "arrivalDateTime")
    private String mArrivalDateTime = Constants.EMPTY;
    @SerializedName("CarrierEntity")
    @ColumnInfo(name = "carrierId")
    private long mCarrierId;
    @SerializedName("OperatingCarrier")
    @ColumnInfo(name = "operatingCarrierId")
    private long mOperatingCarrierId;
    @SerializedName("Duration")
    @ColumnInfo(name = "duration")
    private long mDuration;
    @SerializedName("FlightNumber")
    @NonNull
    @ColumnInfo(name = "flightNumber")
    private String mFlightNumber = Constants.EMPTY;
    @SerializedName("JourneyMode")
    @NonNull
    @ColumnInfo(name = "journeyMode")
    private String mJourneyMode = Constants.EMPTY;
    @SerializedName("Directionality")
    @NonNull
    @ColumnInfo(name = "directionality")
    private String mDirectionality = Constants.EMPTY;

    @Ignore
    public SegmentEntity() {}

    public SegmentEntity(long mId,
                         @NonNull String mOriginalStation,
                         @NonNull String mDestinationStation,
                         @NonNull String mDepartureDateTime,
                         @NonNull String mArrivalDateTime,
                         long mCarrierId,
                         long mOperatingCarrierId,
                         long mDuration,
                         @NonNull String mFlightNumber,
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
        this.mFlightNumber = mFlightNumber;
        this.mJourneyMode = mJourneyMode;
        this.mDirectionality = mDirectionality;
    }

    public long getId() {
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
    public String getFlightNumber() {
        return mFlightNumber;
    }

    @NonNull
    public String getJourneyMode() {
        return mJourneyMode;
    }

    @NonNull
    public String getDirectionality() {
        return mDirectionality;
    }

    public void setId(long mId) {
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

    public void setFlightNumber(@NonNull String mFlightNumber) {
        this.mFlightNumber = mFlightNumber;
    }

    public void setJourneyMode(@NonNull String mJourneyMode) {
        this.mJourneyMode = mJourneyMode;
    }

    public void setDirectionality(@NonNull String mDirectionality) {
        this.mDirectionality = mDirectionality;
    }
}
