package com.chaplin.test3.core.model.mapper;

import com.chaplin.test3.core.model.SearchResultModel;
import com.chaplin.test3.core.utils.DataFormatUtils;
import com.chaplin.test3.data.model.enitity.SearchResultEntity;

public class SearchResultModelMapper {

    public SearchResultModel map(SearchResultEntity entity) {
        SearchResultModel model = new SearchResultModel();

        model.setPrice(DataFormatUtils.formatItineraryPrice(entity.getPrice()));
        model.setAgentName(DataFormatUtils.formatAgentName(entity.getAgentName()));

        // outbbounding
        model.setOutboundingDuration(DataFormatUtils.formatFlightDuration(entity.getOutboundingDuration()));
        model.setOutboundingSummary(DataFormatUtils.formatFlightSummary(entity.getOutboundingOriginalPlaceCode(),
                entity.getOutboundingDestinationPlaceCode(), entity.getOutboundingCarrierName()));
        model.setOutboundingTiming(DataFormatUtils.formatFlightTiming(entity.getOutboundingDepartureDateTime(),
                entity.getOutboundingArrivalDateTime()));
        model.setOutboundingFlightType(DataFormatUtils.formatFlightType(entity.getOutboundingSegmentsCount()));
        // inbounding
        model.setInboundingDuration(DataFormatUtils.formatFlightDuration(entity.getInboundingDuration()));
        model.setInboundingSummary(DataFormatUtils.formatFlightSummary(entity.getInboundingOriginalPlaceCode(),
                entity.getInboundingDestinationPlaceCode(), entity.getInboundingCarrierName()));
        model.setInboundingTiming(DataFormatUtils.formatFlightTiming(entity.getInboundingDepartureDateTime(),
                entity.getInboundingArrivalDateTime()));
        model.setInboundingFlightType(DataFormatUtils.formatFlightType(entity.getInboundingSegmentsCount()));

        return model;
    }
}
