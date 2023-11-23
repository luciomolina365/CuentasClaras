package com.ttps2023.CuentasClaras.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.repositories.CrewRepository;

@Service
public class CrewService {

    private final CrewRepository crewRepository;

    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }
    
//    public Boolean existCrewName(String crewname) {
//		 return crewRepository.findByCrewName(crewname).isPresent()) }
	
	public Boolean exists(Long id) {
		return crewRepository.existsById(id);
	}
	
	public void create(Crew crew) {
		crewRepository.save(crew);
	}

	public Optional<Crew> getById(Long id) {
		Optional<Crew> aux= crewRepository.findById(id);
		Crew aux2 = aux.orElse(null);
		
		
		System.out.println(aux2.getMembersList().toString());
		
		return aux;
		
//		return crewRepository.findById(id);
	}
	
//	
//	 public List<User> getAllMembersList(Long crewId){
//		 return 
//	 }
	 
	 
	
	
	
}
