/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package net.ion.oadr2.utils;

import org.codehaus.jackson.map.util.ISO8601Utils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ISOUtils {


	public static String format(long timeInMillis){
		return ISO8601Utils.format(new Date(timeInMillis));
	}

	public static String format(long timeInMillis, boolean millis){
		return ISO8601Utils.format(new Date(timeInMillis), millis);
	}

	public static String format(){
		return ISO8601Utils.format(new Date(System.currentTimeMillis()));
	}

	public static XMLGregorianCalendar newXMLGregorianCalendar(long timeInMillis, boolean millis) {
		try {
			GregorianCalendar gregorianCal = (GregorianCalendar) Calendar.getInstance();
			gregorianCal.setTimeInMillis(timeInMillis);
			XMLGregorianCalendar d = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCal);

			if (!millis){
				d.setMillisecond(Integer.MIN_VALUE);
			}

			d = d.normalize();
			return d;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static XMLGregorianCalendar newXMLGregorianCalendar(long timeInMillis) {
		return newXMLGregorianCalendar(timeInMillis, false);
	}

	public static XMLGregorianCalendar newXMLGregorianCalendar() {
		return newXMLGregorianCalendar(System.currentTimeMillis(), false);
	}

	public static void main(String[] args) {
		System.out.println(ISOUtils.format(System.currentTimeMillis()));
		System.out.println(ISOUtils.format(System.currentTimeMillis(), true));
		System.out.println(ISOUtils.format());
	}
}
