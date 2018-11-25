package com.chaplin.test3.data.db.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.chaplin.test3.data.model.enitity.SegmentEntity;

@Dao
public interface SegmentDao extends BaseEntityDao<SegmentEntity> {

    @Query("DELETE FROM segments")
    void deleteAll();
}
