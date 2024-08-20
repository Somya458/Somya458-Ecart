package com.user.service.app.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.app.exception.UserAlreadyExistsException;
import com.user.service.app.model.UserInfo;
import com.user.service.app.service.UserService;
import com.user.service.app.service.ValidUser;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private ValidUser validUser;
	@Autowired
	private UserService userService;


	@PostMapping("/signup")
	ResponseEntity<String> signup(@RequestBody Map<String, String> signupDetails) {
		if (!validUser.isPhoneNumberUnique(signupDetails.get("phonenumber"))) {
			//return new ResponseEntity<>("This Phone number already taken", HttpStatus.BAD_REQUEST);
			throw new UserAlreadyExistsException("User already exist with this  phone number: " + signupDetails.get("phonenumber"));
			
		}

		return userService.signUp(signupDetails);

	}

	@PostMapping(value = "/login")
	public ResponseEntity<String> login(@RequestBody Map<String, String> login) {
		if (validUser.isPhoneNumberUnique(login.get("phonenumber"))) {
			return new ResponseEntity<>("Please enter a valid phone number", HttpStatus.BAD_REQUEST);
		}
		if (!validUser.isPasswordValid(login.get("phonenumber"), login.get("password"))) {
			return new ResponseEntity<>("Password do not match!", HttpStatus.BAD_REQUEST);

		}

		return userService.login(login);
	}

	@PostMapping(value = "/logout")
	public ResponseEntity<String> logout(@RequestBody Map entity) {
		return userService.logout(entity);
	}

	@PostMapping(value = "/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> entity) {
		if (validUser.isPhoneNumberUnique(entity.get("phonenumber"))) {
			return new ResponseEntity<>("Please enter a valid phone number", HttpStatus.BAD_REQUEST);
		}
		return userService.forgotPassword(entity);

	}

	@PostMapping(value = "/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> entity) {

		return userService.resetPassword(entity);

	}
	@PostMapping("/find-user-with-phone")
	public ResponseEntity<UserInfo> findUserByPhoneNumber(@RequestBody Map<String, String> entity) {
	    ResponseEntity<UserInfo> responseEntity = userService.getUserInfoWithPhone(entity);

	    if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }

	    return ResponseEntity.ok(responseEntity.getBody());
	}

}
