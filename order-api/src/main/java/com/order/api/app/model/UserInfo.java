package com.order.api.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;

	private String name;

	
	private String phonenumber;

	private String secretquestion;

	private String address;

	private String answer;

	private String password;

	@Column(name = "login_status", unique = false, updatable = true, nullable = false)
	private Boolean loginstatus;

	@Column(name = "role", unique = false, updatable = true, nullable = false)
	private Integer role = 1;

	private String email;
	
}
