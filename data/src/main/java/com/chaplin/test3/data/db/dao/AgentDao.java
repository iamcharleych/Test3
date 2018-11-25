package com.chaplin.test3.data.db.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.chaplin.test3.data.model.enitity.AgentEntity;

@Dao
public interface AgentDao extends BaseEntityDao<AgentEntity> {

    @Query("DELETE FROM agents")
    void deleteAll();
}