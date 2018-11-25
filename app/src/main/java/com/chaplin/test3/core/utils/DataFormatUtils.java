package com.chaplin.test3.core.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Simple formatter that use hardcoded strings for demo purpose instead of String resources
 */
@SuppressLint("ConstantLocale")
public class DataFormatUtils {

    private static final String DIRECT_FLIGHT = "Direct";
    private static final String INDIRECT_FLIGHT_TEMPLATE = "%d Change(s)";
    private static final String FLIGHT_SUMMARY_TEMPLATE = "%s-%s, %s";
    private static final String FLIGHT_DURATION_MIN_TEMPLATE = "%dm";
    private static final String FLIGHT_DURATION_HRS_TEMPLATE = "%dh";
    private static final String FLIGHT_INPUT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String FLIGHT_OUTPUT_TIME_FORMAT = "HH:mm";
    private static final String SCREEN_TITLE_OUTPUT_DATE_FORMAT = "dd MMM";

    private static final long MIN_PER_HOUR = 60;

    private static final DateFormat sDateFormatter = new SimpleDateFormat(FLIGHT_INPUT_DATE_FORMAT, Locale.getDefault());
    private static final DateFormat sScreenTitleDateFormatter = new SimpleDateFormat(SCREEN_TITLE_OUTPUT_DATE_FORMAT, Locale.getDefault());
    private static final DateFormat sTimeFormatter = new SimpleDateFormat(FLIGHT_OUTPUT_TIME_FORMAT, Locale.getDefault());
    private static final PriceFormatter sPriceFormatter = new PriceFormatter();

    public static String formatFlightSummary(String origPlace, String destPlace, String carrierName) {
        return String.format(Locale.getDefault(), FLIGHT_SUMMARY_TEMPLATE, origPlace, destPlace, carrierName);
    }

    public static String formatFlightType(int stopsCount) {
        return stopsCount < 2 ? DIRECT_FLIGHT : String.format(Locale.getDefault(), INDIRECT_FLIGHT_TEMPLATE, stopsCount - 1);
    }

    public static String formatFlightDuration(long duration) {
        final long hours = duration / MIN_PER_HOUR;
        final long minutes = duration % MIN_PER_HOUR;

        StringBuilder sb = new StringBuilder();

        if (hours > 0) {
            sb.append(String.format(Locale.getDefault(), FLIGHT_DURATION_HRS_TEMPLATE, hours));
        }

        if (minutes > 0) {
            sb.append(String.format(Locale.getDefault(), FLIGHT_DURATION_MIN_TEMPLATE, minutes));
        }

        return sb.toString();
    }

    /**
     * Format time range
     * @param departureDateTime String representation of Date in format {@link DataFormatUtils#FLIGHT_INPUT_DATE_FORMAT}
     * @param arrivalDateTime String representation of Date in format {@link DataFormatUtils#FLIGHT_INPUT_DATE_FORMAT}
     * @return time range string in format "HH:mm - HH:mm"
     */
    public static String formatFlightTiming(String departureDateTime, String arrivalDateTime) {
        String departureTime;
        String arrivalTime;
        try {
            Date departureDate = sDateFormatter.parse(departureDateTime);
            Date arrivalDate = sDateFormatter.parse(arrivalDateTime);
            departureTime = sTimeFormatter.format(departureDate);
            arrivalTime = sTimeFormatter.format(arrivalDate);
        } catch (ParseException e) {
            departureTime = departureDateTime;
            arrivalTime = arrivalDateTime;
        }

        return String.format(Locale.getDefault(), "%s - %s", departureTime, arrivalTime);
    }

    public static String formatItineraryPrice(float price) {
        return sPriceFormatter.format(price);
    }

    public static String formatAgentName(String agentName) {
        return String.format(Locale.getDefault(), "via %s", agentName);
    }

    public static String formatSearchScreenTitle(String orig, String dest) {
        return String.format(Locale.getDefault(), "%s - %s", orig, dest);
    }

    public static String formatSearchScreenSubtitle(Date start, Date end, int adults, int children, int infants,
                                                 String cabinClass) {
        StringBuilder sb = new StringBuilder();

        sb.append(sScreenTitleDateFormatter.format(start));
        sb.append(" - ");
        sb.append(sScreenTitleDateFormatter.format(end));

        if (adults > 0) {
            sb.append(String.format(Locale.getDefault(), ", %d adult%s", adults, adults > 1 ? "s" : ""));
        }

        if (children > 0) {
            sb.append(String.format(Locale.getDefault(), ", %d %s", children, children > 1 ? "children" : "child"));
        }

        if (infants > 0) {
            sb.append(String.format(Locale.getDefault(), ", %d infant%s", infants, infants > 1 ? "s" : ""));
        }

        if (!TextUtils.isEmpty(cabinClass)) {
            sb.append(", ").append(cabinClass);
        }

        return sb.toString();
    }
}
