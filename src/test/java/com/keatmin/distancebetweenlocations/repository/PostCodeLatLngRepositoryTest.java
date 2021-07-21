package com.keatmin.distancebetweenlocations.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.keatmin.distancebetweenlocations.entity.PostCodeLatLng;
import com.keatmin.distancebetweenlocations.repository.PostCodeLatLngSpringDataReposiory;


@DataJpaTest
public class PostCodeLatLngRepositoryTest {


	@Autowired
	private PostCodeLatLngSpringDataReposiory repository;
	
	@Test
	public void  findByPostCode() {
		String postCode = "AB14 0TQ";
		Optional<PostCodeLatLng> postCodeLatLng = repository.findByPostCode(postCode);
		assertTrue(postCodeLatLng.isPresent());
	}
	
	@Test
	public void savePostLatLng() {
		String postCode = "AB16 6SZ";
		Optional<PostCodeLatLng> postCodeLatLng = repository.findByPostCode(postCode);
		assertTrue(postCodeLatLng.isPresent());
		
	
		assertEquals(BigDecimal.valueOf(57.158751).doubleValue(),postCodeLatLng.get().getLatitude().doubleValue());
		assertEquals(BigDecimal.valueOf(-2.165215).doubleValue(),postCodeLatLng.get().getLongitude().doubleValue());
		
		
		if ( postCodeLatLng.isPresent() ) {
			postCodeLatLng.get().setLatitude(BigDecimal.valueOf(57.158752));
			postCodeLatLng.get().setLongitude(BigDecimal.valueOf(-2.165216));
			repository.save(postCodeLatLng.get());
		}
			
		Optional<PostCodeLatLng> revisedpostCodeLatLng = repository.findByPostCode(postCode);
		assertTrue(revisedpostCodeLatLng .isPresent());
		assertEquals(BigDecimal.valueOf(57.158752).doubleValue(),revisedpostCodeLatLng.get().getLatitude().doubleValue());
		assertEquals(BigDecimal.valueOf(-2.165216).doubleValue(),revisedpostCodeLatLng.get().getLongitude().doubleValue());
		
	}

	
}
