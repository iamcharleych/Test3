package com.chaplin.test3.data;

import java.util.Calendar;
import java.util.Date;

public class Constants {
    public static final String EMPTY = "";

    public static final int ADULTS_COUNT = 1;
    public static final int CHILDREN_COUNT = 0;
    public static final int INFANTS_COUNT = 0;
    public static final String CABIN_CLASS = "economy";
    public static final String SEARCH_ORIGIN = "Edinburgh";
    public static final String SEARCH_DESTINATION = "London";
    public static final Date OUTBOUND_DATE;
    public static final Date RETURN_DATE;

    static {
        Calendar cal = Calendar.getInstance();

        cal.set(2018, Calendar.DECEMBER, 5);
        OUTBOUND_DATE = new Date(cal.getTimeInMillis());

        cal.set(2018, Calendar.DECEMBER, 6);
        RETURN_DATE = new Date(cal.getTimeInMillis());
    }
}
