/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package net.ion.oadr2.service.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TimeZones {

	public static class TimeZone{
		private String displayName;
		private String time;

		public TimeZone(String time, String displayName){
			this.time = time;
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}

		public String getTime() {
			return time;
		}
		
	}
	private static List<TimeZone> timeZoneList = new ArrayList<TimeZone>();

	static {
		timeZoneList.add(new TimeZone("-12:00", "(UTC-12:00) International Date Line West"));
		timeZoneList.add(new TimeZone("-11:00", "(UTC-11:00) Midway Island, Samoa"));
		timeZoneList.add(new TimeZone("-10:00", "(UTC-10:00) Hawaii"));
		timeZoneList.add(new TimeZone("-09:00", "(UTC-09:00) Alaska"));
		timeZoneList.add(new TimeZone("-08:00", "(UTC-08:00) Pacific Time (US and Canada); Tijuana"));
		timeZoneList.add(new TimeZone("#1-07:00", "(UTC-07:00) Mountain Time (US and Canada)"));
		timeZoneList.add(new TimeZone("#2-07:00", "(UTC-07:00) Chihuahua, La Paz, Mazatlan"));
		timeZoneList.add(new TimeZone("#3-07:00", "(UTC-07:00) Arizona"));
		timeZoneList.add(new TimeZone("#1-06:00", "(UTC-06:00) Central Time (US and Canada)"));
		timeZoneList.add(new TimeZone("#2-06:00", "(UTC-06:00) Saskatchewan"));
		timeZoneList.add(new TimeZone("#3-06:00", "(UTC-06:00) Guadalajara, Mexico City, Monterrey"));
		timeZoneList.add(new TimeZone("#4-06:00", "(UTC-06:00) Central America"));
		timeZoneList.add(new TimeZone("#1-05:00", "(UTC-05:00) Eastern Time (US and Canada)"));
		timeZoneList.add(new TimeZone("#2-05:00", "(UTC-05:00) Indiana (East)"));
		timeZoneList.add(new TimeZone("#3-05:00", "(UTC-05:00) Bogota, Lima, Quito"));
		timeZoneList.add(new TimeZone("#1-04:00", "(UTC-04:00) Atlantic Time (Canada)"));
		timeZoneList.add(new TimeZone("#2-04:00", "(UTC-04:00) Caracas, La Paz"));
		timeZoneList.add(new TimeZone("#3-04:00", "(UTC-04:00) Santiago"));
		timeZoneList.add(new TimeZone("-03:30", "(UTC-03:30) Newfoundland and Labrador"));
		timeZoneList.add(new TimeZone("#1-03:00", "(UTC-03:00) Brasilia"));
		timeZoneList.add(new TimeZone("#2-03:00", "(UTC-03:00) Buenos Aires, Georgetown"));
		timeZoneList.add(new TimeZone("#3-03:00", "(UTC-03:00) Greenland"));
		timeZoneList.add(new TimeZone("-02:00", "(UTC-02:00) Mid-Atlantic"));
		timeZoneList.add(new TimeZone("#1-01:00", "(UTC-01:00) Azores"));
		timeZoneList.add(new TimeZone("#2-01:00", "(UTC-01:00) Cape Verde Islands"));
		timeZoneList.add(new TimeZone("#1+00:00", "(UTC) Greenwich Mean Time: Dublin, Edinburgh, Lisbon, London"));
		timeZoneList.add(new TimeZone("#2+00:00", "(UTC) Casablanca, Monrovia"));
		timeZoneList.add(new TimeZone("#1+01:00", "(UTC+01:00) Belgrade, Bratislava, Budapest, Ljubljana, Prague"));
		timeZoneList.add(new TimeZone("#2+01:00", "(UTC+01:00) Sarajevo, Skopje, Warsaw, Zagreb"));
		timeZoneList.add(new TimeZone("#3+01:00", "(UTC+01:00) Brussels, Copenhagen, Madrid, Paris"));
		timeZoneList.add(new TimeZone("#4+01:00", "(UTC+01:00) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna"));
		timeZoneList.add(new TimeZone("#5+01:00", "(UTC+01:00) West Central Africa"));
		timeZoneList.add(new TimeZone("#1+02:00", "(UTC+02:00) Bucharest"));
		timeZoneList.add(new TimeZone("#2+02:00", "(UTC+02:00) Cairo"));
		timeZoneList.add(new TimeZone("#3+02:00", "(UTC+02:00) Helsinki, Kiev, Riga, Sofia, Tallinn, Vilnius"));
		timeZoneList.add(new TimeZone("#4+02:00", "(UTC+02:00) Athens, Istanbul, Minsk"));
		timeZoneList.add(new TimeZone("#5+02:00", "(UTC+02:00) Jerusalem"));
		timeZoneList.add(new TimeZone("#6+02:00", "(UTC+02:00) Harare, Pretoria"));
		timeZoneList.add(new TimeZone("#1+03:00", "(UTC+03:00) Moscow, St. Petersburg, Volgograd"));
		timeZoneList.add(new TimeZone("#2+03:00", "(UTC+03:00) Kuwait, Riyadh"));
		timeZoneList.add(new TimeZone("#3+03:00", "(UTC+03:00) Nairobi"));
		timeZoneList.add(new TimeZone("#4+03:00", "(UTC+03:00) Baghdad"));
		timeZoneList.add(new TimeZone("+03:30", "(UTC+03:30) Tehran"));
		timeZoneList.add(new TimeZone("#1+04:00", "(UTC+04:00) Abu Dhabi, Muscat"));
		timeZoneList.add(new TimeZone("#2+04:00", "(UTC+04:00) Baku, Tbilisi, Yerevan"));
		timeZoneList.add(new TimeZone("+04:30", "(UTC+04:30) Kabul"));
		timeZoneList.add(new TimeZone("#1+05:00", "(UTC+05:00) Ekaterinburg"));
		timeZoneList.add(new TimeZone("#2+05:00", "(UTC+05:00) Islamabad, Karachi, Tashkent"));
		timeZoneList.add(new TimeZone("+05:30", "(UTC+05:30) Chennai, Kolkata, Mumbai, New Delhi"));
		timeZoneList.add(new TimeZone("+05:45", "(UTC+05:45) Kathmandu"));
		timeZoneList.add(new TimeZone("#1+06:00", "(UTC+06:00) Astana, Dhaka"));
		timeZoneList.add(new TimeZone("#2+06:00", "(UTC+06:00) Sri Jayawardenepura"));
		timeZoneList.add(new TimeZone("#3+06:00", "(UTC+06:00) Almaty, Novosibirsk"));
		timeZoneList.add(new TimeZone("+06:30", "(UTC+06:30) Yangon Rangoon"));
		timeZoneList.add(new TimeZone("#1+07:00", "(UTC+07:00) Bangkok, Hanoi, Jakarta"));
		timeZoneList.add(new TimeZone("#2+07:00", "(UTC+07:00) Krasnoyarsk"));
		timeZoneList.add(new TimeZone("#1+08:00", "(UTC+08:00) Beijing, Chongqing, Hong Kong SAR, Urumqi"));
		timeZoneList.add(new TimeZone("#2+08:00", "(UTC+08:00) Kuala Lumpur, Singapore"));
		timeZoneList.add(new TimeZone("#3+08:00", "(UTC+08:00) Taipei"));
		timeZoneList.add(new TimeZone("#4+08:00", "(UTC+08:00) Perth"));
		timeZoneList.add(new TimeZone("#5+08:00", "(UTC+08:00) Irkutsk, Ulaanbaatar"));
		timeZoneList.add(new TimeZone("#1+09:00", "(UTC+09:00) Seoul"));
		timeZoneList.add(new TimeZone("#2+09:00", "(UTC+09:00) Osaka, Sapporo, Tokyo"));
		timeZoneList.add(new TimeZone("#3+09:00", "(UTC+09:00) Yakutsk"));
		timeZoneList.add(new TimeZone("#1+09:30", "(UTC+09:30) Darwin"));
		timeZoneList.add(new TimeZone("#2+09:30", "(UTC+09:30) Adelaide"));
		timeZoneList.add(new TimeZone("#1+10:00", "(UTC+10:00) Canberra, Melbourne, Sydney"));
		timeZoneList.add(new TimeZone("#2+10:00", "(UTC+10:00) Brisbane"));
		timeZoneList.add(new TimeZone("#3+10:00", "(UTC+10:00) Hobart"));
		timeZoneList.add(new TimeZone("#4+10:00", "(UTC+10:00) Vladivostok"));
		timeZoneList.add(new TimeZone("#5+10:00", "(UTC+10:00) Guam, Port Moresby"));
		timeZoneList.add(new TimeZone("+11:00", "(UTC+11:00) Magadan, Solomon Islands, New Caledonia"));
		timeZoneList.add(new TimeZone("#1+12:00", "(UTC+12:00) Fiji Islands, Kamchatka, Marshall Islands"));
		timeZoneList.add(new TimeZone("#2+12:00", "(UTC+12:00) Auckland, Wellington"));
		timeZoneList.add(new TimeZone("+13:00", "(UTC+13:00) Nuku'alofa"));
		
	}


	private TimeZones(){

	}

	public static List<TimeZone> getTimeZoneList() {
		return timeZoneList;
	}
	

}