package net.unix.wallpaper.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Unix
 * @since 28.07.2020
 */

public final class DateUtil {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    private DateUtil() {
    }

    public static String getDate(long time) {
        return dateFormat.format(time);
    }

    public static String getTime(long time) {
        return timeFormat.format(time);
    }

}