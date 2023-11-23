package com.ttps2023.CuentasClaras.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps2023.CuentasClaras.model.Crew;

public interface CrewRepository extends JpaRepository<Crew, Long> {
	public Optional<Crew> findByCrewName(String crewName);  //fijarse si falla x el crewname
	public Crew getReferenceByCrewName(String crewName);
	public Crew getByCrewName(String crewName);
	
	
	
}
