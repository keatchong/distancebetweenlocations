package com.keatmin.distancebetweenlocations.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "requestlog")
public class RequestLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private long id;

	@Column(name = "postcode1")
	private String postCode1;

	@Column(name = "postcode2")
	private String postCode2;

	@Column(name = "requestdatetime")
	private Timestamp requestDateTime;

	public RequestLog() {

	}

	public RequestLog(String postCode1, String postCode2, LocalDateTime requestDateTime) {

		this.postCode1 = postCode1;
		this.postCode2 = postCode2;
		this.requestDateTime = Timestamp.valueOf(requestDateTime);
	}

	public long getId() {
		return id;
	}

	public String getPostCode1() {
		return postCode1;
	}

	public void setPostCode1(String postCode1) {
		this.postCode1 = postCode1;
	}

	public String getPostCode2() {
		return postCode2;
	}

	public void setPostCode2(String postCode2) {
		this.postCode2 = postCode2;
	}

	public Timestamp getRequestDateTime() {
		return requestDateTime;
	}

	public void setRequestDateTime(Timestamp requestDateTime) {
		this.requestDateTime = requestDateTime;
	}

}
