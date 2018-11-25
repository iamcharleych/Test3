package com.chaplin.test3.data.model.enitity;

import androidx.annotation.NonNull;
import androidx.room.*;
import com.chaplin.test3.data.Constants;

@Entity(tableName = "leg_2_segment_map", foreignKeys = {
        @ForeignKey(entity = LegEntity.class, parentColumns = "id", childColumns = "legId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = SegmentEntity.class, parentColumns = "id", childColumns = "segmentId", onDelete = ForeignKey.CASCADE)
}, indices = {@Index("legId"), @Index("segmentId")})
public class Leg2SegmentEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;
    @NonNull
    @ColumnInfo(name = "legId")
    private String mLegId = Constants.EMPTY;
    @ColumnInfo(name = "segmentId")
    private long mSegmentId;

    @Ignore
    public Leg2SegmentEntity() {}

    public Leg2SegmentEntity(long mId, @NonNull String mLegId, long mSegmentId) {
        this.mId = mId;
        this.mLegId = mLegId;
        this.mSegmentId = mSegmentId;
    }

    public long getId() {
        return mId;
    }

    public String getLegId() {
        return mLegId;
    }

    public long getSegmentId() {
        return mSegmentId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public void setLegId(@NonNull String mLegId) {
        this.mLegId = mLegId;
    }

    public void setSegmentId(long mSegmentId) {
        this.mSegmentId = mSegmentId;
    }
}
