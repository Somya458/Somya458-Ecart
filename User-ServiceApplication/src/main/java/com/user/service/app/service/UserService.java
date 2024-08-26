package com.user.service.app.service;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.user.service.app.exception.UserNotFoundException;
import com.user.service.app.model.UserInfo;
import com.user.service.app.repository.UserInfoRepo;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	// @Autowired
	// private AuthClient authClient;

	@Autowired
	private UserInfoRepo userInfoRepo;

	public ResponseEntity<String> signUp(Map<String, String> signup) {

		UserInfo userInfo = new UserInfo();
		userInfo.setName(signup.get("name"));
		userInfo.setPhonenumber(signup.get("phonenumber"));
		userInfo.setSecretquestion(signup.get("secretquestion"));
		userInfo.setAddress(signup.get("address"));
		userInfo.setAnswer(signup.get("answer"));
		userInfo.setPassword(signup.get("password"));
		userInfo.setLoginstatus(Boolean.FALSE);
		userInfo.setRole(Integer.parseInt(signup.get("role")));

		userInfo = userInfoRepo.save(userInfo);

		return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);

	}

	public ResponseEntity<String> login(Map<String, String> login) {

		Optional<UserInfo> userInfoOptional = userInfoRepo.findByPhonenumber(login.get("phonenumber"));
		if (userInfoOptional.isEmpty()) {
			throw new UserNotFoundException("User not found with phone number: " + login.get("phonenumber"));
		}
		UserInfo userInfo = userInfoOptional.get();
		logger.info("Role" + +userInfo.getRole());
		if (userInfo.getRole() == 0) {
			logger.info("Inside user Role" + +userInfo.getRole());
			userInfo.setLoginstatus(Boolean.TRUE);
			userInfo = userInfoRepo.save(userInfo);
			// Prepare the authentication request for token generation
			/*
			 * AuthenticationRequest authenticationRequest = new AuthenticationRequest();
			 * //authenticationRequest.setUsername(login.get("phonenumber"));
			 * //authenticationRequest.setPassword(login.get("password"));
			 * //authenticationRequest.setUsername("2323234646"); // Assuming username is
			 * phone number //authenticationRequest.setPassword("1234");
			 * 
			 * String token = authClient.generateToken(authenticationRequest); if (token ==
			 * null) { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
			 * body("Invalid username or password"); }
			 */

			return new ResponseEntity<>("User Logged_in Successfully!", HttpStatus.OK);

		} else {
			userInfo.setLoginstatus(Boolean.TRUE);

			userInfo = userInfoRepo.save(userInfo);

			return new ResponseEntity<>("Admin Logged_in Successfully!", HttpStatus.OK);
		}

	}

	public ResponseEntity<String> logout(Map entity) {
		/*
		 * Optional<UserInfo> userInfo =
		 * userInfoRepo.findByPhonenumber(String.valueOf(entity.get("phonenumber")));
		 * //logger.info("phone" + userInfo.get().getPhonenumber()); UserInfo userInfo1
		 * = userInfo.get(); userInfo1.setLoginstatus(Boolean.FALSE); userInfo1 =
		 * userInfoRepo.save(userInfo1);
		 */ 
		// java 8 conversion
		userInfoRepo.findByPhonenumber(String.valueOf(entity.get("phonenumber")))
        .map(user -> {
            user.setLoginstatus(Boolean.FALSE);
            return userInfoRepo.save(user);
        });
		return ResponseEntity.ok().body("Logged out successfully!");
	}

	// to get the secret ques from phonenumber
	public ResponseEntity<String> forgotPassword(Map<String, String> forgotPassword) {

		Optional<UserInfo> userInfo = userInfoRepo.findByPhonenumber(forgotPassword.get("phonenumber"));
		UserInfo userInfo1 = userInfo.get();
		return new ResponseEntity<>(userInfo1.getSecretquestion(), HttpStatus.OK);

	}

	public ResponseEntity<String> resetPassword(Map<String, String> forgotPassword) {

		Optional<UserInfo> userInfo = userInfoRepo.findByPhonenumber(forgotPassword.get("phonenumber"));
		UserInfo userInfo1 = userInfo.get();
		if (userInfo1.getSecretquestion().equals(forgotPassword.get("secretquestion"))) {
			if (userInfo1.getAnswer().equals(forgotPassword.get("answer"))) {
				userInfo1.setPassword(forgotPassword.get("newpassword"));
				userInfo1 = userInfoRepo.save(userInfo1);

			} else {
				return new ResponseEntity<>("Wrong security answer", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
			}
		}
		return new ResponseEntity<>("Password reset successfully!", HttpStatus.ACCEPTED);

	}

	public ResponseEntity<UserInfo> getUserInfoWithPhone(Map<String, String> entity) {
		String phoneNumber = entity.get("phonenumber");

		Optional<UserInfo> userInfo = userInfoRepo.findByPhonenumber(phoneNumber);

		if (userInfo.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		}

		return ResponseEntity.ok(userInfo.get());
	}
}
