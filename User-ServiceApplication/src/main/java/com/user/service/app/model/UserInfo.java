package com.user.service.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "user_info")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, updatable = false, nullable = false)
	private Integer userid;

	@Column(name = "name", unique = false, updatable = true, nullable = false)
	private String name;

	@Column(name = "phone_number", unique = true, updatable = false, nullable = false)
	@Size(min = 10, max = 10, message = "Phone number must be 10 digits long")
	// @Pattern(regexp = "^$|^\\d{10}$", message = "should be empty or 10 digits")
	private String phonenumber;

	@Column(name = "secret_question", unique = false, updatable = false, nullable = false)
	private String secretquestion;

	@Column(name = "address", unique = false, updatable = false, nullable = false)
	private String address;

	@Column(name = "answer", unique = false, updatable = false, nullable = false)
	private String answer;

	@Column(name = "password", unique = false, updatable = true, nullable = false)
	private String password;

	@Column(name = "login_status", unique = false, updatable = true, nullable = false)
	private Boolean loginstatus;

	@Column(name = "role", unique = false, updatable = true, nullable = false)
	private Integer role = 1;

	
	// @JsonManagedReference
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo")
	// private List<StudentSolvedQuiz> solvedQuizList = new
	// ArrayList<StudentSolvedQuiz>();

}
