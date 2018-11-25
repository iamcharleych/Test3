package com.chaplin.test3.data.db.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.chaplin.test3.data.model.enitity.PlaceEntity;

@Dao
public interface PlaceDao extends BaseEntityDao<PlaceEntity> {

    @Query("DELETE FROM places")
    void deleteAll();
}
