package com.chaplin.test3.data.db.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.chaplin.test3.data.model.enitity.CarrierEntity;

@Dao
public interface CarrierDao extends BaseEntityDao<CarrierEntity> {

    @Query("DELETE FROM carriers")
    void deleteAll();
}
