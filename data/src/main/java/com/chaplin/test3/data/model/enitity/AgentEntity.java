package com.chaplin.test3.data.model.enitity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.chaplin.test3.data.Constants;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "agents")
public class AgentEntity {

    @SerializedName("Id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    private long mId;
    @SerializedName("Name")
    @NonNull
    @ColumnInfo(name = "name")
    private String mName = Constants.EMPTY;
    @SerializedName("Status")
    @NonNull
    @ColumnInfo(name = "status")
    private String mStatus = Constants.EMPTY;
    @SerializedName("Type")
    @NonNull
    @ColumnInfo(name = "type")
    private String mType = Constants.EMPTY;
    @SerializedName("ImageUrl")
    @ColumnInfo(name = "imageUrl")
    private String mImageUrl;

    @Ignore
    public AgentEntity() {}

    public AgentEntity(long mId, @NonNull String mName, @NonNull String mStatus, @NonNull String mType, String mImageUrl) {
        this.mId = mId;
        this.mName = mName;
        this.mStatus = mStatus;
        this.mType = mType;
        this.mImageUrl = mImageUrl;
    }

    public long getId() {
        return mId;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getType() {
        return mType;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public void setName(@NonNull String mName) {
        this.mName = mName;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
