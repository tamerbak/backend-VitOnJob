package com.vitonjob.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static String getDayName(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		switch (dayOfWeek) {
		case 1:
			return "Dimanche";

		case 2:
			return "Lundi";

		case 3:
			return "Mardi";

		case 4:
			return "Mercredi";

		case 5:
			return "Jeudi";

		case 6:
			return "Vendredi";

		case 7:
			return "Samedi";

		default:
			return null;
		}
	}

	public static Date getDateTime(Date date, Integer heureDeDebutEnMinutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, heureDeDebutEnMinutes);

		return calendar.getTime();
	}

	public static Long getDuration(Date startDate, Date endDate) {
		return (endDate.getTime() - startDate.getTime()) / 1000;
	}

	public static Date addOrRemoveDays(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, n);
		return calendar.getTime();
	}

	public static String getDurationTextFromSecondes(long secondes) {
		StringBuilder durationText = new StringBuilder();

		int hours = (int) secondes / 3600;
		durationText.append(hours + "h ");

		int remainder = (int) secondes - hours * 3600;
		int mins = remainder / 60;
		durationText.append(mins + "m ");

		// remainder = remainder - mins * 60;
		// int secs = remainder;
		// durationText.append(secs + "s");

		return durationText.toString();
	}

	public static void main(String[] args) {
		System.out.println(getDurationTextFromSecondes(8520L));
	}

}
