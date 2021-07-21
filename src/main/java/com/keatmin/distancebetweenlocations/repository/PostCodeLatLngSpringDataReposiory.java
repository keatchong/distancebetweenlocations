package com.keatmin.distancebetweenlocations.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keatmin.distancebetweenlocations.entity.PostCodeLatLng;

public interface PostCodeLatLngSpringDataReposiory extends JpaRepository<PostCodeLatLng, Long> {

	Optional<PostCodeLatLng> findByPostCode(String postCode);

}
