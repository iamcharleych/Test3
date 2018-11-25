package com.chaplin.test3.data.db.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;
import com.chaplin.test3.data.model.enitity.SearchResultEntity;

@Dao
public interface SearchResultsDao {

    @Query("SELECT" +
            " itineraries.outboundLegId," +
            " itineraries.inboundLegId," +
            " pricing_options.price as price," +
            " agents.name as agentName," +
            " outLeg.arrivalDateTime as outArrivalDateTime," +
            " outLeg.departureDateTime as outDepartureDateTime," +
            " outLeg.duration as outDuration," +
            " outCarrier.name as outCarrierName," +
            " outPlacesOrig.code as outOriginalPlaceCode," +
            " outPlacesDest.code as outDestinationPlaceCode," +

            " (SELECT COUNT(*)" +
            " FROM leg_2_segment_map" +
            " WHERE legId = outLeg.id" +
            ") as outSegmentsCount," +

            " inLeg.arrivalDateTime as inArrivalDateTime," +
            " inLeg.departureDateTime as inDepartureDateTime," +
            " inLeg.duration as inDuration," +
            " inCarrier.name as inCarrierName," +
            " inPlacesOrig.code as inOriginalPlaceCode," +
            " inPlacesDest.code as inDestinationPlaceCode, " +

            " (SELECT COUNT(*)" +
            " FROM leg_2_segment_map" +
            " WHERE legId = inLeg.id" +
            ") as inSegmentsCount" +

            " FROM itineraries" +
            " JOIN pricing_options ON" +
            " (itineraries.outboundLegId = pricing_options.outboundLegId AND" +
            " itineraries.inboundLegId = pricing_options.inboundLegId) " +
            " JOIN agents ON agents.id = pricing_options.agentId " +
            " JOIN legs AS outLeg ON itineraries.outboundLegId = outLeg.id " +
            " JOIN carriers AS outCarrier ON outLeg.carrierId = outCarrier.id " +
            " JOIN places AS outPlacesOrig ON outLeg.originalStation = outPlacesOrig.id " +
            " JOIN places AS outPlacesDest ON outLeg.destinationStation = outPlacesDest.id " +
            " JOIN legs AS inLeg ON itineraries.outboundLegId = inLeg.id " +
            " JOIN carriers AS inCarrier ON inLeg.carrierId = inCarrier.id " +
            " JOIN places AS inPlacesOrig ON inLeg.originalStation = inPlacesOrig.id " +
            " JOIN places AS inPlacesDest ON inLeg.destinationStation = inPlacesDest.id ")
    DataSource.Factory<Integer, SearchResultEntity> loadSearchResults();
}
