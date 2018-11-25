package com.chaplin.test3.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.chaplin.test3.data.model.enitity.ItineraryEntity;
import com.chaplin.test3.data.model.enitity.PricingOptionEntity;

import java.util.List;

@Dao
public interface ItineraryDao extends BaseEntityDao<ItineraryEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPricingOptions(List<PricingOptionEntity> pricingOptions);

    @Query("DELETE FROM itineraries")
    void deleteAll();

    @Query("DELETE FROM pricing_options")
    void deleteAllPricingOptions();
}
