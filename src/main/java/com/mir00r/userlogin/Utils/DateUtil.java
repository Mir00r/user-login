package com.mir00r.userlogin.Utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
public class DateUtil {
    private DateUtil() {
    }

    public static final String SERVER_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_PATTERN_BACKWARDS = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN_BACKWARDS = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_PATTERN_READABLE = "MMM dd, YYYY";
    public static final String DATE_PATTERN_READABLE_MONTH_YEAR = "MMM, YY";
    public static final String DATE_PATTERN_READABLE_DAY_NAME = "MMM dd, YYYY EEEE";
    public static final String DATE_PATTERN_DAY_MONTH_NAME = "MMMM dd, YYYY EEEE";
    public static final String TIME_PATTERN_READABLE = "hh:mm a";
    public static final String DATE_TIME_PATTERN_READABLE = "MMM dd, YYYY hh:mm a";
    public static final String DATE_PATTERN_SMS_REPORT = "dd-MM-yy: hh-mm";
    public static final String DATE_PATTERN_SMS_REPORT_TEMP = "dd-MM-yy";
    public static final String DATE_PATTERN_DOTTED = "dd.MM.yy";
    public static final String DATE_PATTERN_MONTH = "MMMM";


    public static String getReadableDateDotted(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat(DATE_PATTERN_DOTTED).format(date);
    }

    public static DateFormat getReadableDateFormatSlashed() {
        return new SimpleDateFormat("YYYY/MM/dd");
    }

    public static String getReadableDateSlashed(Date date) {
        return getReadableDateFormatSlashed().format(date);
    }

    public static String getReadableDateWithDayName(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String getReadableDate(Date date) {
        return getReadableDateFormat().format(date);
    }

    public static String getReadableDateWithDayName(Date date) {
        return new SimpleDateFormat(DATE_PATTERN_READABLE_DAY_NAME).format(date);
    }

    public static String getReadableTime(Date date) {
        return getReadableTimeFormat().format(date);
    }

    public static String getReadableDateTime(Date date) {
        return getReadableDateTimeFormat().format(date);
    }


    public static Date parseServerDateTime(String date) throws ParseException {
        DateFormat sdf = getServerDateTimeFormat();
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.parse(date);
    }

    public static DateFormat getReadableDateFormat() {
        return new SimpleDateFormat(DATE_PATTERN_READABLE);
    }

    public static DateFormat getReadableTimeFormat() {
        return new SimpleDateFormat(TIME_PATTERN_READABLE);
    }

    public static DateFormat getReadableDateTimeFormat() {
        return new SimpleDateFormat(DATE_TIME_PATTERN_READABLE);
    }

    public static DateFormat getServerDateTimeFormat() {
        return new SimpleDateFormat(SERVER_DATE_TIME_PATTERN);
    }

    public static DateFormat getBackwardDateFormat() {
        return new SimpleDateFormat(DATE_PATTERN_BACKWARDS);
    }


    public static java.time.Period getAge(Date date) {
        if (date == null) return null;
        LocalDate today = LocalDate.now();
        LocalDate birthday = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return java.time.Period.between(birthday, today);
    }

    public static Date getBirthdayForAge(int age) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -age);
        return cal.getTime();
    }

    public static long getDurationInDays(Date date) {
        if (date == null) return 0L;
        LocalDate today = LocalDate.now();
        LocalDate myDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(myDate, today);
    }

    public static long getDurationInMonths(Date date) {
        if (date == null) return 0L;
        LocalDate today = LocalDate.now();
        LocalDate myDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.MONTHS.between(myDate, today);
    }

    public static List<Date> getDatesBetween(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }

    public static List<Date> getWeeksBetween(Date startDate, Date endDate) {
        List<Date> weeksInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        calendar.setFirstDayOfWeek(Calendar.SATURDAY);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            // Set the calendar to monday of the current week
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

            // Print dates of the current week starting on Monday
            DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy");
            for (int i = 0; i < 7; i++) {
                calendar.add(Calendar.DATE, 1);
            }
            Date result = calendar.getTime();
            weeksInRange.add(result);
        }
        return weeksInRange;
    }


    public static String getMonthNameFromDate(Date date) {
        Format formatter = new SimpleDateFormat("MMMM");
        return formatter.format(date);
    }
    public static boolean isInCurrentMonthYear(Date date) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal2.setTime(date);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
    }

    public static boolean isPast(Date date) {
        return date.before(new Date());
    }

    public enum DateRangeType {
        DATE_FROM("dateFrom"),
        DATE_TO("dateTo");

        private String value;


        DateRangeType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public enum Period {
        THIS_WEEK, THIS_MONTH, LAST_MONTH, THIS_YEAR, LAST_YEAR, ALL_TIME
    }

    public enum Months {
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
    }

    public static Period getPeriod(String period) {
        switch (period) {
            case "this_week":
                return Period.THIS_WEEK;
            case "this_month":
                return Period.THIS_MONTH;
            case "last_month":
                return Period.LAST_MONTH;
            case "this_year":
                return Period.THIS_YEAR;
            case "last_year":
                return Period.LAST_YEAR;
            default:
                return Period.ALL_TIME;
        }
    }


    public static Set<Date> buildMonthsFromDateRange(Date startDate, Date endDate) {
        LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Set<Date> dates = new TreeSet<>();

        LocalDate date = start;
        if (date.getDayOfMonth() == 1) {
            date = date.minusDays(1);
        }

        while (date.isBefore(end)) {
            if (date.plusMonths(1).with(lastDayOfMonth()).isAfter(end)) {
                break;
            }

            date = date.plusMonths(1).withDayOfMonth(1);
            dates.add(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        return dates;
    }

    public static Date addMonths(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static Date addOneYear(Date date) {
        if (date == null) return null;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, 1);
        date = c.getTime();
        return date;
    }
}
