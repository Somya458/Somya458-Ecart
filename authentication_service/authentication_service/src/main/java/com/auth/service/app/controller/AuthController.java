package com.auth.service.app.controller;

import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.service.app.model.AuthenticationRequest;
import com.auth.service.app.model.User;
import com.auth.service.app.repository.UserRepository;
import com.auth.service.app.service.AuthService;
import com.auth.service.app.util.JwtUtil;
import com.user.service.app.exception.InvalidTokenException;
import com.user.service.app.exception.UserAlreadyExistsException;
import com.user.service.app.exception.UserNotFoundException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RestController
public class AuthController {
	@Autowired
	private AuthService service;
	@Autowired
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;

		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/*
	 * @PostMapping("/token") public String createAuthenticationToken(@RequestBody
	 * AuthenticationRequest authenticationRequest) throws Exception {
	 * authenticationManager.authenticate( new
	 * UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
	 * authenticationRequest.getPassword()) ); return
	 * service.generateToken(authenticationRequest.getUsername()); }
	 */
	@PostMapping("/token")
	public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			String token = service.generateToken(authenticationRequest.getUsername());
			return ResponseEntity.ok(token);
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Invalid username or password");
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND)
					.body("User not found with username: " + authenticationRequest.getUsername());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
					.body("An error occurred during authentication");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		try {
			String response = service.saveUser(user);
			return ResponseEntity.status(HttpStatus.SC_CREATED).body(response);
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(e.getMessage());
		}
	}

	/*
	 * @GetMapping("/secured-endpoint") public String securedEndpoint() { return
	 * "This is a secured endpoint"; }
	 */
	/*
	 * @GetMapping("/validate") public String validateToken(@RequestParam("token")
	 * String token) { service.validateToken(token); return "Token is valid"; }
	 */
	@GetMapping("/validate")
	public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
		try {
			service.validateToken(token);
			return ResponseEntity.status(HttpStatus.SC_OK).body("Token is valid");

		} catch (InvalidTokenException e) {
			return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
					.body("Invalid token");
		}

	}
}
