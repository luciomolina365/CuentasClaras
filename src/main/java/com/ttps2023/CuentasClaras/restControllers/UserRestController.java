package com.ttps2023.CuentasClaras.restControllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps2023.CuentasClaras.DTO.Credentials;
import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.services.TokenServices;
import com.ttps2023.CuentasClaras.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

	private final UserService userService;
//	private final HttpSession httpSession;
	private final TokenServices tokenServices;
	private final int EXPIRATION_IN_SEC = 100; // 10 segs!!

	@Autowired
	public UserRestController(UserService userService, TokenServices tokenServices) {
		this.userService = userService;
		this.tokenServices = tokenServices;
	}

	@PostMapping("/create")
	public ResponseEntity<Object> createUser(@RequestBody User user) {

		if (user == null || user.getUsername() == null || user.getPass() == null) {
			return new ResponseEntity<>("Datos de usuario incorrectos o faltantes.", HttpStatus.BAD_REQUEST);

		}

		if (userService.existsByUsername(user.getUsername())) {
			return new ResponseEntity<>("Usuario ya existe", HttpStatus.CONFLICT);

		}

		userService.create(user);

		return new ResponseEntity<Object>(user, HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Map<String, String> credentials) {

		if (!credentials.containsKey("username") || !credentials.containsKey("pass")) {
			return new ResponseEntity<>("Campos obligatorios faltantes en la solicitud.", HttpStatus.BAD_REQUEST);
		}

		String username = credentials.get("username");
		String password = credentials.get("pass");

		if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
			return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
		}

		Optional<User> userQuery = userService.authenticateWithUsernameAndPass(username, password);
		User user = userQuery.orElse(null);

		if (user != null) {
			return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
		}

		String token = tokenServices.generateToken(user.getUsername(), EXPIRATION_IN_SEC);
		return new ResponseEntity<>(new Credentials(token, EXPIRATION_IN_SEC, user.getUsername()), HttpStatus.OK);
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {

		return new ResponseEntity<>("Cierre de sesión exitoso", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<User> userQuery = userService.getById(id);

		User user = userQuery.orElse(null);
		if (user == null) {
			return new ResponseEntity<>("Usuario no encontrado.", HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
