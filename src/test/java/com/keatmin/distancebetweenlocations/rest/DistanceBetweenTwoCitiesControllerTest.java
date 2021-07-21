package com.keatmin.distancebetweenlocations.rest;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.keatmin.distancebetweenlocations.entity.PostCodeLatLng;
import com.keatmin.distancebetweenlocations.repository.PostCodeLatLngSpringDataReposiory;
import com.keatmin.distancebetweenlocations.response.DistanceResponseObj;
import com.keatmin.distancebetweenlocations.rest.DistanceRestController;
import com.keatmin.distancebetweenlocations.service.DistanceBetweenlocationsService;

// unit test for controller

@WebMvcTest(DistanceRestController.class)
public class DistanceBetweenTwoCitiesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DistanceBetweenlocationsService service; // mocking business service

	
	

	@WithMockUser(username = "admin", password = "qwerty0908&", roles = { "USER" })
	@Test
	public void getLatLng_test() throws Exception {

		String postCode = "AB11 5QN";
		when(service.getLatLng(postCode)).thenReturn(
				new PostCodeLatLng("AB11 5QN", BigDecimal.valueOf(57.14270100), BigDecimal.valueOf(-2.093295000)));

		RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/getLatLng/{postCode}", postCode)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content()
						.json("{\"postCode\":\"AB11 5QN\",\"latitude\":57.142701000,\"longitude\":-2.093295000}"))
				.andReturn();

	}
	
	
	@Test
	@WithMockUser(username = "admin", password = "qwerty0908&", roles = { "USER" })
	public void getDistanceRespose_test() throws Exception {

		String postCode = "AB10 1XG";
		String postCode2 = "AB16 5ST";

		when(service.getDistanceBetweenTwoLocation(postCode, postCode2)).thenReturn(
			new DistanceResponseObj( 
					new PostCodeLatLng("AB10 1XG",BigDecimal.valueOf(57.144165000),
							BigDecimal.valueOf(-2.114848000)),
		
					new PostCodeLatLng("AB16 5ST",BigDecimal.valueOf(57.163466000),
							BigDecimal.valueOf(-2.159333000)),7d)	
		);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/v1/getDistance?loc1={loc1}&loc2={loc2}", postCode, postCode2)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(
						"{\"firstlocation\":{\"postCode\":\"AB10 1XG\",\"latitude\":\"57°8'39\\\"N\",\"longitude\":\"2°6'53\\\"S\"},\"secondlocation\":{\"postCode\":\"AB16 5ST\",\"latitude\":\"57°9'48\\\"N\",\"longitude\":\"2°9'34\\\"S\"},\"distance\":7.0,\"unit\":\"KM\"}"))
				.andReturn();

		

	}

}
