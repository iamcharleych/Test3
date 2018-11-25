package com.chaplin.test3.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.chaplin.test3.data.model.enitity.FlightNumberEntity;
import com.chaplin.test3.data.model.enitity.Leg2SegmentEntity;
import com.chaplin.test3.data.model.enitity.LegEntity;

import java.util.List;

@Dao
public interface LegDao extends BaseEntityDao<LegEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFlightNumbers(List<FlightNumberEntity> flightNumbers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLeg2Segments(List<Leg2SegmentEntity> leg2Segments);

    @Query("DELETE FROM legs")
    void deleteAll();
}
