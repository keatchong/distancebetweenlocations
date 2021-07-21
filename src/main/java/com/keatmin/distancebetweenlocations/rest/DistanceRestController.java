package com.keatmin.distancebetweenlocations.rest;

import java.math.BigDecimal;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.keatmin.distancebetweenlocations.entity.PostCodeLatLng;
import com.keatmin.distancebetweenlocations.service.DistanceBetweenlocationsService;

@RestController
@Validated
@RequestMapping("/api/v1")

public class DistanceRestController {

	@Autowired
	private DistanceBetweenlocationsService distanceService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/getDistance")
	public ResponseEntity<Object> getDistanceBetweenTwoLocation(@RequestParam String loc1, @RequestParam String loc2) {
		logger.info("loc1={}, loc2={}", loc1, loc2);
		return ResponseEntity.ok().body(distanceService.getDistanceBetweenTwoLocation(loc1, loc2));
	}

	@GetMapping("/getLatLng/{postCode}")
	public ResponseEntity<PostCodeLatLng> getLatLng(@PathVariable String postCode) {
		return ResponseEntity.ok().body(distanceService.getLatLng(postCode));
	}

	@PostMapping("/updateLatLng/{postCode}")
	public ResponseEntity<PostCodeLatLng> updateLatLng(@RequestBody Map<String, String> update,
			@PathVariable String postCode) {
		return ResponseEntity.ok().body(distanceService.updateLatLng(update, postCode));
	}

}
