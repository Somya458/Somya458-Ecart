package com.auth.service.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.service.app.model.User;
import com.auth.service.app.repository.UserRepository;
import com.auth.service.app.util.JwtUtil;
import com.user.service.app.exception.UserAlreadyExistsException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	public String saveUser(User user) {
		Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

		if (existingUser.isPresent()) {
			throw new UserAlreadyExistsException("User already exists with username: " + user.getUsername());
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "User registered successfully";

	}

	public String generateToken(String username) {
		return jwtUtil.generateToken(username);
	}

	public void validateToken(String token) {
		jwtUtil.validateToken(token);

		
		
	}

}
