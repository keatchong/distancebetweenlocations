package com.keatmin.distancebetweenlocations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keatmin.distancebetweenlocations.entity.RequestLog;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {

}
