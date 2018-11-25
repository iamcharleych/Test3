package com.chaplin.test3.data.model.enitity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.chaplin.test3.data.Constants;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "places")
public class PlaceEntity {

    @SerializedName("Id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    private long mId;
    @SerializedName("ParentId")
    @ColumnInfo(name = "parentId")
    private long mParentId;
    @SerializedName("Code")
    @NonNull
    @ColumnInfo(name = "code")
    private String mCode = Constants.EMPTY;
    @SerializedName("Name")
    @NonNull
    @ColumnInfo(name = "name")
    private String mName = Constants.EMPTY;
    @SerializedName("Type")
    @NonNull
    @ColumnInfo(name = "type")
    private String mType = Constants.EMPTY;

    @Ignore
    public PlaceEntity() {}

    public PlaceEntity(long mId, long mParentId, @NonNull String mCode, @NonNull String mName, @NonNull String mType) {
        this.mId = mId;
        this.mParentId = mParentId;
        this.mCode = mCode;
        this.mName = mName;
        this.mType = mType;
    }

    public long getId() {
        return mId;
    }

    public long getParentId() {
        return mParentId;
    }

    @NonNull
    public String getCode() {
        return mCode;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getType() {
        return mType;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public void setParentId(long mParentId) {
        this.mParentId = mParentId;
    }

    public void setCode(@NonNull String mCode) {
        this.mCode = mCode;
    }

    public void setName(@NonNull String mName) {
        this.mName = mName;
    }

    public void setType(@NonNull String mType) {
        this.mType = mType;
    }
}
