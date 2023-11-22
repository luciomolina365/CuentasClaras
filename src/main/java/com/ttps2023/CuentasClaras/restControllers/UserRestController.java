package com.ttps2023.CuentasClaras.restControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		if (userService.exists(user)) {
			return new ResponseEntity<String>("Usuario ya existe", HttpStatus.CONFLICT);
		}

		userService.create(user);
		return new ResponseEntity<String>("Usuario creado", HttpStatus.CREATED);
	}

	@PostMapping("/authentication")
	public ResponseEntity<String> authUser(@RequestBody Map<String, Object> authRequest) {

		String username = (String) authRequest.get("username");
		String pass = (String) authRequest.get("pass");

		Optional<User> userQuery = userService.authenticateWithUsernameAndPass(username, pass);

		User user = userQuery.orElse(null);
		if (user == null) {
			return new ResponseEntity<String>("Usuario no encontrado.", HttpStatus.FORBIDDEN);
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Token", user.getId() + "/123456");
		
		return ResponseEntity.accepted().headers(responseHeaders).body(null);
	}

}
