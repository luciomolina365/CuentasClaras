package com.ttps2023.CuentasClaras.restControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.services.UserService;
import com.ttps2023.CuentasClaras.filter.*;

import java.util.Map;
import java.util.Optional;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.ttps2023.CuentasClaras.services.TokenServices;


@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

	private final UserService userService;
	private final TokenServices tokenServices;

	public UserRestController(UserService userService, TokenServices tokenServices) {
		this.userService = userService;
		this.tokenServices=tokenServices;
	}

	private final int EXPIRATION_IN_SEC = 100; // 10 segs!!
	
//	@PostMapping(path = "/auth")
//	public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) { //el signo de pregunta CAMBIAR
//	    String username = credentials.get("username");
//	    String password = credentials.get("password");
//
//	    try {
//	        Optional<User> userQuery = userService.authenticateWithUsernameAndPass(username, password);
//	        
//	        if (userQuery.isPresent()) {
//	            String token = TokenServices.generateToken(username, EXPIRATION_IN_SEC);
//	            return ResponseEntity.ok(new Credentials(token, EXPIRATION_IN_SEC, username));
//	        } else {
//	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
//	        }
//	    } catch (Exception e) {
//	        // Manejar excepciones adecuadamente, loguearlas, etc.
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error durante la autenticación");
//	    }
//	}
	
	
	
	
	  @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) { //el signo de pregunta CAMBIAR
		    String username = credentials.get("username");
		    String password = credentials.get("pass");
		    
		        Optional<User> userQuery = userService.authenticateWithUsernameAndPass(username, password);
		        User user = userQuery.orElse(null); 
		        System.out.println(user);
		        if (user != null) {

	            // Generar token
	       
	            String token = tokenServices.generateToken(user.getUsername(),EXPIRATION_IN_SEC);

	            // Devolver el token en la respuesta
	            return ResponseEntity.ok(new Credentials(token, EXPIRATION_IN_SEC, user.getUsername()));
	            }
		        else {
		        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
	        }
	    }
	


	@PostMapping("/create") 
	public ResponseEntity<String> createUser(@RequestBody User user) {    // @Validated envia un internal sever error 
		
		if (userService.existsByUsername(user.getUsername())) {											
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
	
//	@GetMapping("/alt/{id}")
//	public ResponseEntity<String> getById(@PathVariable Long id, @RequestHeader("Token") String token) {
//		
//
//		Optional<User> userQuery = userService.getById(id);
//		
//		User user = userQuery.orElse(null);
//		if (user == null) {
//			return new ResponseEntity<String>("Usuario no encontrado.", HttpStatus.NOT_FOUND);
//		}
//		
//		if (!token.equals(user.getId()+"/123456")) {
//			return new ResponseEntity<String>("Usuario no encontrado.", HttpStatus.UNAUTHORIZED);
//		}
//		
//		
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        String userJson = null;
//        try {
//			userJson = objectMapper.writeValueAsString(user);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//        
//		return ResponseEntity.ok().body(userJson); 
//	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> altGetById(@PathVariable Long id, @RequestHeader("Token") String token) {
		

		Optional<User> userQuery = userService.getById(id);
		
		User user = userQuery.orElse(null);
		if (user == null) {
			return new ResponseEntity<Object>("Usuario no encontrado.", HttpStatus.NOT_FOUND);
		}
		
		if (!token.equals(user.getId()+"/123456")) {
			return new ResponseEntity<Object>("Usuario no encontrado.", HttpStatus.UNAUTHORIZED);
		}		      
		
		return ResponseEntity.ok().body(user); 
	}

}
