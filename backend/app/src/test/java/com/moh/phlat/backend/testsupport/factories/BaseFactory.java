package com.moh.phlat.backend.testsupport.factories;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class BaseFactory {
    protected static Date createDate(int year, int month, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.set(year, month, dayOfMonth);
        return calendar.getTime();
    }
}
