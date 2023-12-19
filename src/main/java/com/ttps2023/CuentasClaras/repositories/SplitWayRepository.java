package com.ttps2023.CuentasClaras.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ttps2023.CuentasClaras.model.SplitWay;

public interface SplitWayRepository extends JpaRepository<SplitWay, Long> {
	
	@Query(value = "SELECT split_way_type FROM split_way WHERE splitway_id = :splitWayId", nativeQuery = true)
	public String findSplitWayTypeById(@Param("splitWayId") Long splitWayId);



	
}