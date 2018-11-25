package com.chaplin.test3.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.chaplin.test3.data.db.dao.*;
import com.chaplin.test3.data.model.enitity.*;

@Database(
        entities = {
                AgentEntity.class,
                CarrierEntity.class,
                FlightNumberEntity.class,
                ItineraryEntity.class,
                LegEntity.class,
                Leg2SegmentEntity.class,
                PlaceEntity.class,
                PricingOptionEntity.class,
                SegmentEntity.class
        },
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "testapp_database.db";

    public abstract AgentDao getAgentDao();
    public abstract CarrierDao getCarrierDao();
    public abstract ItineraryDao getItineraryDao();
    public abstract LegDao getLegDao();
    public abstract SegmentDao getSegmentDao();
    public abstract PlaceDao getPlaceDao();
    public abstract SearchResultsDao getSearchResultsDao();

}
