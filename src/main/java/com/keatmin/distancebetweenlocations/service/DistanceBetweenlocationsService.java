package com.keatmin.distancebetweenlocations.service;

import static com.keatmin.distancebetweenlocationsutility.DistanceCalculator.calculateDistance;
import static com.keatmin.distancebetweenlocationsutility.DistanceCalculator.roundUp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.keatmin.distancebetweenlocations.entity.PostCodeLatLng;
import com.keatmin.distancebetweenlocations.entity.RequestLog;
import com.keatmin.distancebetweenlocations.exceptionhandling.PostCodeNotFoundException;
import com.keatmin.distancebetweenlocations.repository.PostCodeLatLngSpringDataReposiory;
import com.keatmin.distancebetweenlocations.repository.RequestLogRepository;
import com.keatmin.distancebetweenlocations.response.DistanceResponseObj;

@Component
public class DistanceBetweenlocationsService {

	@Autowired
	private PostCodeLatLngSpringDataReposiory latLngRepository;

	@Autowired
	private RequestLogRepository requestLogRepository;

	public DistanceResponseObj getDistanceBetweenTwoLocation(String postcode1, String postcode2) {

		PostCodeLatLng latLng1 = latLngRepository.findByPostCode(postcode1)
				.orElseThrow(() -> new PostCodeNotFoundException(postcode1));
		PostCodeLatLng latLng2 = latLngRepository.findByPostCode(postcode2)
				.orElseThrow(() -> new PostCodeNotFoundException(postcode2));

		double distance = calculateDistance(latLng1.getLatitude().floatValue(), latLng1.getLongitude().floatValue(),
				latLng2.getLatitude().floatValue(), latLng2.getLongitude().floatValue());
		distance = roundUp(distance, 3);

		requestLogRepository.save(new RequestLog(postcode1, postcode2, LocalDateTime.now()));

		return new DistanceResponseObj(latLng1, latLng2, distance);

	}

	public PostCodeLatLng getLatLng(String postCode) {

		return latLngRepository.findByPostCode(postCode).orElseThrow(() -> new PostCodeNotFoundException(postCode));
	}

	@Transactional
	public PostCodeLatLng updateLatLng(Map<String, String> update, String postCode) {

		return latLngRepository.findByPostCode(postCode).map((p) -> {
			String latitude = update.get("latitude");
			String longitude = update.get("longitude");
			if (StringUtils.hasLength(latitude) && StringUtils.hasLength(longitude)) {
				p.setLatitude(new BigDecimal(latitude));
				p.setLongitude(new BigDecimal(longitude));
				return latLngRepository.save(p);
			} else {
				throw new RuntimeException();
			}
		}).orElseGet(() -> {
			throw new PostCodeNotFoundException(postCode);
		});

	}

}
