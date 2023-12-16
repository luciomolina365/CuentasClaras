package com.ttps2023.CuentasClaras.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttps2023.CuentasClaras.model.CrewCategory;
import com.ttps2023.CuentasClaras.repositories.CrewCategoryRepository;

@Service
public class CrewCategoryService {

	private final CrewCategoryRepository crewCategoryRepository;

	public CrewCategoryService(CrewCategoryRepository crewCategoryRepository) {
		this.crewCategoryRepository = crewCategoryRepository;
	}
	
	public Optional<CrewCategory> getById(Long id){
		return crewCategoryRepository.findById(id);
	}
	
	
	
	
}
