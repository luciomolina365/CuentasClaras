package com.ttps2023.CuentasClaras.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps2023.CuentasClaras.model.SplitWay;

public interface SplitWayRepository extends JpaRepository<SplitWay, Long> {
	public SplitWay findByName(String name);
	public Optional<SplitWay> findById(Long id);
}