package com.keatmin.distancebetweenlocations.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.keatmin.distancebetweenlocations.entity.PostCodeLatLng;
import com.keatmin.distancebetweenlocations.repository.PostCodeLatLngSpringDataReposiory;
import com.keatmin.distancebetweenlocations.service.DistanceBetweenlocationsService;

//Unit test DistanceBetweenTwoCitiesService
@ExtendWith(MockitoExtension.class)
public class DistanceBetweenlocationsServiceTest {

	@InjectMocks
	private DistanceBetweenlocationsService service;

	@Mock
	PostCodeLatLngSpringDataReposiory latLngRepository;

	
	@Test
	public void getLatLng_test() {
		
		String postCode = "AB11 5QN";
		when(latLngRepository.findByPostCode(postCode)).thenReturn(
				 Optional.of(new PostCodeLatLng("AB11 5QN",BigDecimal.valueOf(57.142701000),
							BigDecimal.valueOf(-2.093295000) )
						 )
				);
						
		 PostCodeLatLng latLng = service.getLatLng(postCode);	
		 assertEquals("AB11 5QN",latLng.getPostCode());
		 assertEquals(BigDecimal.valueOf(57.142701000).doubleValue(),latLng.getLatitude().doubleValue());
		 assertEquals(BigDecimal.valueOf(-2.093295000).doubleValue() ,latLng.getLongitude().doubleValue());
	}

}
