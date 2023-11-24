package com.ttps2023.CuentasClaras.restControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.services.CrewService;

import jakarta.persistence.Column;



@RestController
@RequestMapping(value = "/crew", produces = MediaType.APPLICATION_JSON_VALUE)
public class CrewRestController {

		private final CrewService crewService;

		public CrewRestController(CrewService crewService) {
			this.crewService = crewService;
		}
		
		@PostMapping("/create")
		public ResponseEntity<String> createCrew(@RequestBody Crew crew) {
			crewService.create(crew);
			return new ResponseEntity<String>("Grupo creado", HttpStatus.CREATED);
		}
		
		
		 @PutMapping("/update/{id}")
		 public Crew update(@RequestBody Map<String, Object> crewRequest, @PathVariable("id") Long crewId) {
			 String name =(String) crewRequest.get("name");
			 Boolean isActive =(Boolean)crewRequest.get("active");
			 System.out.println("adafafaafnbaiufifaufhvhfavahfvfahvfahvafdvafhjfafasdbfaedafedyuifaedy");
			 System.out.println(crewId);
			 System.out.println(name);
			 System.out.println(isActive);
			 return crewService.updateCrew(crewId, name, isActive);
			 
			
		 

		
		
		 }}
		
		
//		@PostMapping("/update/{id}")
//		public ResponseEntity<String> getById(@PathVariable Long id, @RequestHeader("Token") String token) {
//			
//			Optional<Crew> crewQuery= crewService.getById(id);
//			
//			Crew crew=crewQuery.orElse(null);
//			if (crew==null) {
//				return new ResponseEntity<String>("Grupo no encontrado.", HttpStatus.NOT_FOUND);
//			}
//			
//			if (!token.equals(crew.getId()+"/123456")) {
//				return new ResponseEntity<String>("Grupo no encontrado.", HttpStatus.UNAUTHORIZED);
//			}
//			
//			
//			HttpHeaders responseHeaders = new HttpHeaders();					  //
//			responseHeaders.set("Token", crew.getId() + "/123456");
//		
//			return ResponseEntity.accepted().headers(responseHeaders).body(null);
//		}
		
		
//		public ResponseEntity<String> createUser(@RequestBody Crew crew) {
//			if (crewService.existCrewName(crew.getCrewName())) {
//				return new ResponseEntity<String>("Grupo con ese nombre ya existe", HttpStatus.CONFLICT);
//			}
		
		
		
		
		

