package com.notification.service.app.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification")
public class Notification {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createdAt;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "subject", nullable = false)
	private String subject;

	@Column(name = "message", nullable = false)
	private String message;

	@Column(name = "status", nullable = false)
	private String status;

	public Notification(String email, String subject, String message, String status) {
	    this.email = email;
	    this.subject = subject;
	    this.message = message;
	    this.status = status;
	}
}
