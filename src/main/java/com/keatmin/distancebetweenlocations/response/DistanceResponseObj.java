package com.keatmin.distancebetweenlocations.response;

import com.keatmin.distancebetweenlocations.entity.PostCodeLatLng;

public class DistanceResponseObj {

	public DistanceResponseObj(PostCodeLatLng loc1, PostCodeLatLng loc2, Double distance) {
		this.distance = distance;
		this.firstlocation = new Location(loc1.getPostCode(), loc1.getLatitude(), loc1.getLongitude());
		this.secondlocation = new Location(loc2.getPostCode(), loc2.getLatitude(), loc2.getLongitude());
	}

	private Location firstlocation;

	private Location secondlocation;

	private double distance = 0.000d;

	private String unit = "km";

	public Location getFirstlocation() {
		return firstlocation;
	}

	public void setFirstlocation(Location firstlocation) {
		this.firstlocation = firstlocation;
	}

	public Location getSecondlocation() {
		return secondlocation;
	}

	public void setSecondlocation(Location secondlocation) {
		this.secondlocation = secondlocation;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
