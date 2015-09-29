package com.jkkp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtil {

	public static final String DATE_FORMATTER = "yyyy-MM-dd";
	public static final String DATETIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

	public static String formatDate(Date date) {
		return date == null ? "" : format(date, DATE_FORMATTER);
	}

	public static String formatDateTime(Date date) {
		return date == null ? "" : format(date, DATETIME_FORMATTER);
	}

	public static String format(Date date, String formatter) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatter);
			return dateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date parse(String dateString) {
		try {
			if (StringUtils.isNotEmpty(dateString)) {
				SimpleDateFormat formatter;
				if (dateString.length() > 10) {
					formatter = new SimpleDateFormat(DATETIME_FORMATTER);
				} else {
					formatter = new SimpleDateFormat(DATE_FORMATTER);
				}
				return formatter.parse(dateString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回当前日期时间
	 * 
	 * @return 例如：2006-06-06 12:12:50
	 */
	public static String getCurDateTime() {
		return getCurDateTime(DATETIME_FORMATTER);
	}

	/**
	 * 根据给定的格式返回当前日期或时间 相当于调用getDateTime(formatStr,Calendar.getInstance()
	 * 
	 * @param formatStr
	 *            日期时间格式 例如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurDateTime(String formatStr) {
		return getDateTime(formatStr, Calendar.getInstance());
	}

	public static String getCurDateTime(DateFormat formatter) {
		return formatter.format(Calendar.getInstance().getTime());
	}

	public static String getDateTime(String formatStr, Date c) {
		SimpleDateFormat nowDate = new SimpleDateFormat(formatStr);
		String curTimeStr = nowDate.format(c.getTime());

		return curTimeStr;
	}

	public static String getDateTime(String formatStr, long time) {
		SimpleDateFormat nowDate = new SimpleDateFormat(formatStr);
		String curTimeStr = nowDate.format(time);

		return curTimeStr;
	}

	public static String getDateTime(String formatStr, Calendar c) {
		SimpleDateFormat nowDate = new SimpleDateFormat(formatStr);
		String curTimeStr = nowDate.format(c.getTime());

		return curTimeStr;
	}
}
