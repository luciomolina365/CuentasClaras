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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps2023.CuentasClaras.filter.Credentials;
import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.services.TokenServices;
import com.ttps2023.CuentasClaras.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

	private final UserService userService;
	private final HttpSession httpSession;
	private final TokenServices tokenServices;
	private final int EXPIRATION_IN_SEC = 100; // 10 segs!!

	@Autowired
	public UserRestController(UserService userService, HttpSession httpSession, TokenServices tokenServices) {
		this.userService = userService;
		this.httpSession = httpSession;
		this.tokenServices = tokenServices;
	}

	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		if (userService.existsByUsername(user.getUsername())) {
			return new ResponseEntity<>("Usuario ya existe", HttpStatus.CONFLICT);
		}
		userService.create(user);

		httpSession.setAttribute("userId", user.getId());

		return new ResponseEntity<>("Usuario creado", HttpStatus.CREATED);
	}

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
	public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) { // el signo de pregunta CAMBIAR
		String username = credentials.get("username");
		String password = credentials.get("pass");

		Optional<User> userQuery = userService.authenticateWithUsernameAndPass(username, password);
		User user = userQuery.orElse(null);

		if (user != null) {

			String token = tokenServices.generateToken(user.getUsername(), EXPIRATION_IN_SEC);

			return ResponseEntity.ok(new Credentials(token, EXPIRATION_IN_SEC, user.getUsername()));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
		}
	}

	@PostMapping("/authentication")
	public ResponseEntity<String> authUser(@RequestBody Map<String, Object> authRequest) {
		String username = (String) authRequest.get("username");
		String pass = (String) authRequest.get("pass");

		Optional<User> userQuery = userService.authenticateWithUsernameAndPass(username, pass);

		User user = userQuery.orElse(null);
		if (user == null) {
			return new ResponseEntity<>("Datos incorrectos.", HttpStatus.UNAUTHORIZED);
		}

		httpSession.setAttribute("userId", user.getId());

		return new ResponseEntity<>("Autenticación exitosa", HttpStatus.OK);
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		httpSession.invalidate();
		return new ResponseEntity<>("Cierre de sesión exitoso", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id, @RequestHeader("Token") String token) {
		Optional<User> userQuery = userService.getById(id);

		User user = userQuery.orElse(null);
		if (user == null) {
			return new ResponseEntity<>("Usuario no encontrado.", HttpStatus.NOT_FOUND);
		}

//        Long userId = (Long) httpSession.getAttribute("userId");
//        if (userId == null || !userId.equals(user.getId())) {
//            return new ResponseEntity<>("Usuario no autorizado.", HttpStatus.UNAUTHORIZED);
//        }

		return ResponseEntity.ok().body(user);
	}
}
