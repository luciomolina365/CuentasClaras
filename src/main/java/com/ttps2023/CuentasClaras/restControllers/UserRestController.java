package com.ttps2023.CuentasClaras.restControllers;

import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

	private final UserService userService;
	private final HttpSession httpSession;

	@Autowired
	public UserRestController(UserService userService, HttpSession httpSession) {
		this.userService = userService;
		this.httpSession = httpSession;
	}

	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody User user, HttpServletRequest request) {
		if (userService.existsByUsername(user.getUsername())) {
			return new ResponseEntity<>("Usuario ya existe", HttpStatus.CONFLICT);
		}

		userService.create(user);

		httpSession.setAttribute("userId", user.getId()); 

		return new ResponseEntity<>("Usuario creado", HttpStatus.CREATED);
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
