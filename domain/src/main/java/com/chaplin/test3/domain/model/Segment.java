package com.chaplin.test3.domain.model;


public class Segment {

    private long mId;
    private String mOriginalStation;
    private String mDestinationStation;
    private String mDepartureDateTime;
    private String mArrivalDateTime;
    private long mCarrierId;
    private long mOperatingCarrierId;
    private long mDuration;
    private String mFlightNumber;
    private String mJourneyMode;
    private String mDirectionality;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
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

    public long getCarrierId() {
        return mCarrierId;
    }

    public void setCarrierId(long carrierId) {
        mCarrierId = carrierId;
    }

    public long getOperatingCarrierId() {
        return mOperatingCarrierId;
    }

    public void setOperatingCarrierId(long operatingCarrierId) {
        mOperatingCarrierId = operatingCarrierId;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public String getFlightNumber() {
        return mFlightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        mFlightNumber = flightNumber;
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
}
