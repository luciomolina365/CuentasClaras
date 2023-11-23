package com.ttps2023.CuentasClaras.restControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.services.CrewService;



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
		
//		public ResponseEntity<String> createUser(@RequestBody Crew crew) {
//			if (crewService.existCrewName(crew.getCrewName())) {
//				return new ResponseEntity<String>("Grupo con ese nombre ya existe", HttpStatus.CONFLICT);
//			}
		
		
		
		
		
}
