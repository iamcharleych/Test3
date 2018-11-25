package com.chaplin.test3.domain.model;

import java.util.List;

public class Leg {

    private String mId;
    private String mOriginalStation;
    private String mDestinationStation;
    private String mDepartureDateTime;
    private String mArrivalDateTime;
    private long mDuration;
    private String mJourneyMode;
    private String mDirectionality;
    private List<Long> mCarriersIds;
    private List<Long> mOperatingCarriersIds;
    private List<Long> mSegmentIds;
    private List<FlightNumber> mFlightNumbers;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getOriginalStation() {
        return mOriginalStation;
    }

    public void setOriginalStation(String originalStation) {
        mOriginalStation = originalStation;
    }

    public String getDestinationStation() {
        return mDestinationStation;
    }

    public void setDestinationStation(String destinationStation) {
        mDestinationStation = destinationStation;
    }

    public String getDepartureDateTime() {
        return mDepartureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        mDepartureDateTime = departureDateTime;
    }

    public String getArrivalDateTime() {
        return mArrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        mArrivalDateTime = arrivalDateTime;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public String getJourneyMode() {
        return mJourneyMode;
    }

    public void setJourneyMode(String journeyMode) {
        mJourneyMode = journeyMode;
    }

    public String getDirectionality() {
        return mDirectionality;
    }

    public void setDirectionality(String directionality) {
        mDirectionality = directionality;
    }

    public List<Long> getCarriersIds() {
        return mCarriersIds;
    }

    public void setCarriersIds(List<Long> carriersIds) {
        mCarriersIds = carriersIds;
    }

    public List<Long> getOperatingCarriersIds() {
        return mOperatingCarriersIds;
    }

    public void setOperatingCarriersIds(List<Long> operatingCarriersIds) {
        mOperatingCarriersIds = operatingCarriersIds;
    }

    public List<Long> getSegmentIds() {
        return mSegmentIds;
    }

    public void setSegmentIds(List<Long> segmentIds) {
        mSegmentIds = segmentIds;
    }

    public List<FlightNumber> getFlightNumbers() {
        return mFlightNumbers;
    }

    public void setFlightNumbers(List<FlightNumber> flightNumbers) {
        mFlightNumbers = flightNumbers;
    }
}
