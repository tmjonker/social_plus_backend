package com.tmjonker.socialmediabackend.utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateTimeUtility {

    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Timestamp parseTimestamp(String date) {
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(date).getTime());
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
