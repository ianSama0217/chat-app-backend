package com.example.chat.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "email", length = 45, nullable = false)
	private String email;
	
	@Column(name = "password", length = 60, nullable = false)
	private String password;
	
	@Column(name = "name", length = 45, nullable = false)
	private String name;
	
	@Column(name = "disabled", length = 1, nullable = false)
	private String disabled = "N";
	
	@Column(name = "signup_date", nullable = false)
	private LocalDateTime signupDate;
	
	@Column(name = "update_id", nullable = true)
	private Integer updateId;
}
