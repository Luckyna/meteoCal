package utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author umboDifa
 */
public class TimeTool {

    public static String calendarToTextDay(Calendar day) {
        SimpleDateFormat df = new SimpleDateFormat();
        //df.applyPattern("yyyy-MM-dd hh:mm:ss");
        df.applyPattern("yyyy-MM-dd");

        return df.format(day.getTime());
    }

    public static String dateToTextDay(Date date) {
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyy-MM-dd");
        return df.format(date);

    }

    /**
     * if day1 is before day2 regardless of the time of those days
     *
     * @param day1
     * @param day2
     * @return true if day1 is before day2
     */
    public static boolean isBefore(Calendar day1, Calendar day2) {
        Calendar day1Normalized;
        Calendar day2Normalized;

        //normalizzo i giorni settando le ore a zero 
        day1Normalized = normalize(day1);
        day2Normalized = normalize(day2);

        //uso la compare di libreria
        return day1Normalized.before(day2Normalized);
    }

    /**
     * Returns a clone of the day with 0 hours 0 min and 0 secs and 0 milllisec
     *
     * @param day day to normalize
     * @return A calendar with time all resetted to 0
     */
    public static Calendar normalize(Calendar day) {
        Calendar dayNormalized = (Calendar) day.clone();

        dayNormalized.set(day.get(Calendar.YEAR), day.get(Calendar.MONTH),
                day.get(Calendar.DATE));
        dayNormalized.set(Calendar.HOUR_OF_DAY, 0);
        dayNormalized.set(Calendar.MINUTE, 0);
        dayNormalized.set(Calendar.SECOND, 0);
        dayNormalized.set(Calendar.MILLISECOND, 0);

        return dayNormalized;
    }

    /**
     * if day1 is after day2 regardless of the time of the day
     *
     * @param day1
     * @param day2
     * @return true if day 1 is after day 2
     */
    public static boolean isAfter(Calendar day1, Calendar day2) {
        return !isBefore(day1, day2);
    }

}
