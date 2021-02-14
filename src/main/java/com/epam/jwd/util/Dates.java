package com.epam.jwd.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Util class for formatting dates on the JSP pages
 */
public final class Dates {
    private Dates() {}

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}