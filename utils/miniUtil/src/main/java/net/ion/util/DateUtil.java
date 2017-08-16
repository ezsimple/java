package net.ion.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

	public static String getDurTime(Date start, Date end, String timeUnit) {
        long ms = end.getTime() - start.getTime();
        switch(timeUnit) {
            case "ms":  return TimeUnit.MILLISECONDS.convert(ms, TimeUnit.MILLISECONDS) + " ms";
            case "sec": return TimeUnit.SECONDS.convert(ms, TimeUnit.SECONDS) + " seconds";
            case "min": return TimeUnit.MINUTES.convert(ms, TimeUnit.MINUTES) + " mins";
            case "hour":return TimeUnit.HOURS.convert(ms, TimeUnit.HOURS) + " hours";
        }
        return TimeUnit.MILLISECONDS.convert(ms, TimeUnit.MILLISECONDS) + " ms";
    }

}
