package com.keatmin.distancebetweenlocationsutility;

import java.math.BigDecimal;

public class DecimalToDegreeConverter {

	public static String convert(BigDecimal latitude, BigDecimal longitude) {
		return convertLatitud(latitude) + ' ' + convertLongitude(longitude);
	}

	public static String convertLatitud(BigDecimal latitude) {

		String result = "";
		if (latitude != null) {
			String direction = "N";
			if (latitude.compareTo(BigDecimal.ZERO) < 0) {
				direction = "S";
			}
			result = convert(latitude) + direction;
		}
		return result;
	}

	public static String convertLongitude(BigDecimal longitude) {

		String result = "";
		if (longitude != null) {
			String direction = "E";
			if (longitude.compareTo(BigDecimal.ZERO) < 0) {
				direction = "W";
			}
			result = convert(longitude) + direction;
		}
		return result;
	}

	private static String convert(BigDecimal decimal) {

		decimal = decimal.abs();

		// degrees
		Integer degreeInt = decimal.intValue();

		String s = String.valueOf(degreeInt) + "°";

		// minutes
		BigDecimal minutes = decimal.subtract(BigDecimal.valueOf(degreeInt.longValue()));
		minutes = minutes.multiply(BigDecimal.valueOf(60L));
		Integer minutesInt = minutes.intValue();

		s = s + String.valueOf(minutesInt) + "'";

		// seconds
		BigDecimal seconds = minutes.subtract(BigDecimal.valueOf(minutesInt.longValue()));
		seconds = seconds.multiply(BigDecimal.valueOf(60L));

		s = s + String.valueOf(Math.round(seconds.floatValue())) + "\"";

		return s;
	}

}
