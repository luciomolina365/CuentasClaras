package com.ttps2023.CuentasClaras.restControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.services.UserService;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

	private final UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/hola")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>("Hola test", HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		if (userService.exists(user.getId())) {													//en vez de id no seria username ej
			return new ResponseEntity<String>("Usuario ya existe", HttpStatus.CONFLICT);
		}

		userService.create(user);
		return new ResponseEntity<String>("Usuario creado", HttpStatus.CREATED);
	}

	@PostMapping("/authentication")
	public ResponseEntity<String> authUser(@RequestBody Map<String, Object> authRequest) {
		
//		Pienso en hacerle un service aparte si requiere muchas 
//		cosas en algun momento o necesitamos autenticar otro tipo de usuarios.

		String username = (String) authRequest.get("username");
		String pass = (String) authRequest.get("pass");

		Optional<User> userQuery = userService.authenticateWithUsernameAndPass(username, pass);

		User user = userQuery.orElse(null);
		if (user == null) {
			return new ResponseEntity<String>("Datos incorrectos.", HttpStatus.FORBIDDEN);
		}

		HttpHeaders responseHeaders = new HttpHeaders();					  //
		responseHeaders.set("Token", user.getId() + "/123456");               // HACER FILTRO O MODULO APARTE?
			
		return ResponseEntity.accepted().headers(responseHeaders).body(null); //
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<String> getById(@PathVariable Long id, @RequestHeader("Token") String token) {
		

		Optional<User> userQuery = userService.getById(id);
		
		User user = userQuery.orElse(null);
		if (user == null) {
			return new ResponseEntity<String>("Usuario no encontrado.", HttpStatus.NOT_FOUND);
		}
		
		if (!token.equals(user.getId()+"/123456")) {
			return new ResponseEntity<String>("Usuario no encontrado.", HttpStatus.UNAUTHORIZED);
		}
		
		
        ObjectMapper objectMapper = new ObjectMapper();

        String userJson = null;
        try {
			userJson = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();					  //
		responseHeaders.set("Token", user.getId() + "/123456");               // HACER FILTRO O MODULO APARTE?
	
		return ResponseEntity.ok().headers(responseHeaders).body(userJson); //
	}

}
