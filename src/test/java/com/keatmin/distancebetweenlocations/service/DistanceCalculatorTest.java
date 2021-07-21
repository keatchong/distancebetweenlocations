package com.keatmin.distancebetweenlocations.service;

import static com.keatmin.distancebetweenlocationsutility.DistanceCalculator.calculateDistance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.keatmin.distancebetweenlocations.entity.PostCodeLatLng;
import com.keatmin.distancebetweenlocations.repository.PostCodeLatLngSpringDataReposiory;


// Unit test DistanceCalculator

@ExtendWith(MockitoExtension.class)
public class DistanceCalculatorTest {

	@Mock
	PostCodeLatLngSpringDataReposiory latLngRepository;
	
	@Test
	public void calculateDistance_test() {
		
		String postCode1 = "AB10 1XG";
		String postCode2 = "AB21 0AL";
		
		when(latLngRepository.findByPostCode(postCode1)).thenReturn(
				 Optional.of(new PostCodeLatLng("AB10 1XG",BigDecimal.valueOf(57.144165),
							BigDecimal.valueOf(-2.114848) )
						 )
				);				
		when(latLngRepository.findByPostCode(postCode2)).thenReturn(
				
				 Optional.of(new PostCodeLatLng("AB21 0AL",BigDecimal.valueOf(57.263420),
							BigDecimal.valueOf(-2.158605) )
						 )
				);
		Optional<PostCodeLatLng> loc1 = latLngRepository.findByPostCode("AB10 1XG");
		Optional<PostCodeLatLng> loc2 = latLngRepository.findByPostCode("AB21 0AL");
		
		double distance = calculateDistance(loc1.get().getLatitude().doubleValue(),loc1.get().getLongitude().doubleValue(),
				loc2.get().getLatitude().doubleValue(),loc2.get().getLongitude().doubleValue() );
		
		assertEquals(13.519901675802725,distance);
	
	}
}
