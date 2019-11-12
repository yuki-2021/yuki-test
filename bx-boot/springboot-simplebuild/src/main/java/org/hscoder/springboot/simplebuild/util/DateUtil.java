package org.hscoder.springboot.simplebuild.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String currentDateStr() {
        return dateFormatter.format(LocalDate.now());
    }

    public static String currentDateTimeStr() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    public static String formatDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return dateFormatter.format(localDate);
    }

    public static String formatDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return dateTimeFormatter.format(localDate);
    }

    public static Date toDate(String formattedDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.parse(formattedDate, dateFormatter);
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);

        Date date = Date.from(zdt.toInstant());
        return date;
    }

    public static Date toDateTime(String formattedDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.parse(formattedDate, dateTimeFormatter);
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);

        Date date = Date.from(zdt.toInstant());
        return date;
    }
}
