package com.keatmin.distancebetweenlocations.response;

import java.math.BigDecimal;

import com.keatmin.distancebetweenlocationsutility.DecimalToDegreeConverter;

public class Location {

	public Location(String postCode, BigDecimal latitude, BigDecimal longitude) {

		this.postCode = postCode;
		this.latitude = DecimalToDegreeConverter.convertLatitud(latitude);
		this.longitude = DecimalToDegreeConverter.convertLatitud(longitude);
	}

	private String postCode;

	private String latitude;

	private String longitude;

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
