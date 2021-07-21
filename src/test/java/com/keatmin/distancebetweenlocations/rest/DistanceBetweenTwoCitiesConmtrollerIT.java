package com.keatmin.distancebetweenlocations.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
//Integration Test
public class DistanceBetweenTwoCitiesConmtrollerIT {

	@Autowired
	private TestRestTemplate restTemplate;
	
	
	@Test
	public void test_getDistance() throws JSONException, UnsupportedEncodingException {
		
		String postCode = "AB12 4NA";
		String postCode2 = "AB16 5ST";
		ResponseEntity<String>  response = this.restTemplate.withBasicAuth("admin", "qwerty0908&").getForEntity(
				"/api/v1/getDistance?loc1="+URLEncoder.encode(postCode,StandardCharsets.UTF_8.toString())
				+"&loc2="+URLEncoder.encode(postCode2,StandardCharsets.UTF_8.toString()),String.class);
		
		JSONAssert.assertEquals("{\"firstlocation\":{\"postCode\":\"AB12 4NA\",\"latitude\":\"57°3'51\\\"N\",\"longitude\":\"2°7'48\\\"S\"},\"secondlocation\":{\"postCode\":\"AB16 5ST\",\"latitude\":\"57°9'48\\\"N\",\"longitude\":\"2°9'34\\\"S\"},\"distance\":11.171,\"unit\":\"km\"}", 
					response.getBody(), false);
		
	}
	
	
	@Test
	public void test_getLatLng() throws JSONException, UnsupportedEncodingException {
		
		String postCode = "AB12 4NA";
		ResponseEntity<String>  response = this.restTemplate.withBasicAuth("admin", "qwerty0908&").getForEntity(
				"/api/v1/getLatLng/"+postCode,String.class);

	
		JSONAssert.assertEquals("{\"postCode\":\"AB12 4NA\",\"latitude\":57.064273000,\"longitude\": -2.130018000	}", 
					response.getBody(), false);
		
		
		
	}
	

	
	@Test
	public void test_updateLatLng() throws JSONException, UnsupportedEncodingException {
		

		String postCode = "AB21 7LP";
		String updateLatLngURL = "/api/v1/updateLatLng/"+postCode;
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        JSONObject latLngJsonObject = new JSONObject();
        latLngJsonObject.put("latitude", 57.209903000);
        latLngJsonObject.put("longitude",-2.182892001);
        
		ObjectMapper objectMapper = new ObjectMapper();
		
		HttpEntity<String> request = new HttpEntity<String>( latLngJsonObject.toString(), headers);
		
		ResponseEntity<String> response = restTemplate.withBasicAuth("admin", "qwerty0908&").postForEntity(updateLatLngURL, request, String.class);
		

		
		JSONAssert.assertEquals("{\"postCode\":\"AB21 7LP\",\"latitude\":57.209903000,\"longitude\":-2.182892001}", 
					response.getBody(), false);
		
	}
}
