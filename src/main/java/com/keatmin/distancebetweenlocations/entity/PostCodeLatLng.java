package com.keatmin.distancebetweenlocations.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "postcodelatlng")
public class PostCodeLatLng {

	public PostCodeLatLng() {

	}

	public PostCodeLatLng(String postCode, BigDecimal latitude, BigDecimal longitude) {
		this.postCode = postCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public PostCodeLatLng(Integer id, String postCode) {

		this.id = id;
		this.postCode = postCode;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@JsonIgnore
	private Integer id;

	@Column(name = "postcode")
	private String postCode;

	@Column(precision = 12, scale = 9)
	private BigDecimal latitude;

	@Column(precision = 12, scale = 9)
	private BigDecimal longitude;

	

	public Integer getId() {
		return id;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "PostCodeLatLng [id=" + id + ", postCode=" + postCode + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}

}
