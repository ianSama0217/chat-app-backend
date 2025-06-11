package com.example.chat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_contact")
@Getter
@Setter
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_contact_id")
	private Integer userContactId;
	
	@Column(name = "user_id", nullable = false)
	private Integer userId;
	
	@Column(name = "contact_id", nullable = false)
	private Integer contactId;
}
