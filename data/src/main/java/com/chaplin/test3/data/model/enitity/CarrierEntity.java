package com.chaplin.test3.data.model.enitity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.chaplin.test3.data.Constants;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "carriers")
public class CarrierEntity {

    @SerializedName("Id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    private long mId;
    @SerializedName("Code")
    @NonNull
    @ColumnInfo(name = "code")
    private String mCode = Constants.EMPTY;
    @SerializedName("DisplayCode")
    @NonNull
    @ColumnInfo(name = "displayCode")
    private String mDisplayCode = Constants.EMPTY;
    @SerializedName("Name")
    @NonNull
    @ColumnInfo(name = "name")
    private String mName = Constants.EMPTY;
    @SerializedName("ImageUrl")
    @ColumnInfo(name = "imageUrl")
    private String mImageUrl;

    @Ignore
    public CarrierEntity() {}

    public CarrierEntity(long mId, @NonNull String mCode, @NonNull String mDisplayCode, @NonNull String mName, String mImageUrl) {
        this.mId = mId;
        this.mCode = mCode;
        this.mDisplayCode = mDisplayCode;
        this.mName = mName;
        this.mImageUrl = mImageUrl;
    }

    public long getId() {
        return mId;
    }

    @NonNull
    public String getCode() {
        return mCode;
    }

    @NonNull
    public String getDisplayCode() {
        return mDisplayCode;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public void setCode(@NonNull String mCode) {
        this.mCode = mCode;
    }

    public void setDisplayCode(@NonNull String mDisplayCode) {
        this.mDisplayCode = mDisplayCode;
    }

    public void setName(@NonNull String mName) {
        this.mName = mName;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
