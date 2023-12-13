package com.ttps2023.CuentasClaras.repositories;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ttps2023.CuentasClaras.model.SplitWay;

public interface SplitWayRepository extends JpaRepository<SplitWay, Long> {
	// public SplitWay findByName(String name);
	
	@Query("SELECT sw FROM SplitWay sw WHERE sw.id = :splitWayId")
    public Map<String, Object> findSplitWayById(@Param("splitWayId") Long splitWayId);
}