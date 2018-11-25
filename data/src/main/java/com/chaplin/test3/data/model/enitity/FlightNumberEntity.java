package com.chaplin.test3.data.model.enitity;

import androidx.annotation.NonNull;
import androidx.room.*;
import com.chaplin.test3.data.Constants;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "flight_numbers", foreignKeys = {
        @ForeignKey(entity = LegEntity.class, parentColumns = "id", childColumns = "legId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = CarrierEntity.class, parentColumns = "id", childColumns = "carrierId", onDelete = ForeignKey.CASCADE)
}, indices = {@Index("legId"), @Index("carrierId")})
public class FlightNumberEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    transient private long mId;
    @NonNull
    @ColumnInfo(name = "legId")
    transient private String mLegId = Constants.EMPTY;
    @SerializedName("CarrierId")
    @ColumnInfo(name = "carrierId")
    private long mCarrierId;
    @SerializedName("FlightNumber")
    @NonNull
    @ColumnInfo(name = "number")
    private String mNumber = Constants.EMPTY;

    @Ignore
    public FlightNumberEntity() { }

    public FlightNumberEntity(long mId, @NonNull String mLegId, long mCarrierId, @NonNull String mNumber) {
        this.mId = mId;
        this.mLegId = mLegId;
        this.mCarrierId = mCarrierId;
        this.mNumber = mNumber;
    }

    public long getId() {
        return mId;
    }

    public String getLegId() {
        return mLegId;
    }

    public long getCarrierId() {
        return mCarrierId;
    }

    @NonNull
    public String getNumber() {
        return mNumber;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public void setLegId(String mLegId) {
        this.mLegId = mLegId;
    }

    public void setCarrierId(long mCarrierId) {
        this.mCarrierId = mCarrierId;
    }

    public void setNumber(@NonNull String mNumber) {
        this.mNumber = mNumber;
    }
}
