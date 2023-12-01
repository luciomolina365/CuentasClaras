package com.ttps2023.CuentasClaras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps2023.CuentasClaras.model.CrewCategory;

public interface CrewCategoryRepository extends JpaRepository<CrewCategory, Long> {
}