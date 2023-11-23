package com.ttps2023.CuentasClaras.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.repositories.CrewRepository;

@Service
public class CrewService {

    private final CrewRepository crewRepository;

    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }
	
	public Boolean exists(Long id) {
		return crewRepository.existsById(id);
	}
	
	public void create(Crew crew) {
		crewRepository.save(crew);
	}

	public Optional<Crew> getById(Long id) {
		return crewRepository.findById(id);
	}
	
	
	
	
}
