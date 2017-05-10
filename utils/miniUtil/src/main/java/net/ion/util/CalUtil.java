package net.ion.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalUtil {

	public final static long gmtTime(){
		return GregorianCalendar.getInstance().getTimeInMillis() ;
	}
	
	public final static Calendar newCalendar() {
		return Calendar.getInstance();
	}
}
