package com.chaplin.test3.data.db.dao;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

public interface BaseEntityDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<T> entities);
}
