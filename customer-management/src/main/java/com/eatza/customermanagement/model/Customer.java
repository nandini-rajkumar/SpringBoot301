package com.eatza.customermanagement.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customers")
@Getter @Setter @NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private String place;
	
	private String userName;
	private String password;
	
	private boolean isActive = true;
	
	private LocalDateTime createDate = LocalDateTime.now();

	private LocalDateTime updateDateTime;

	public Customer(String firstName, String lastName, String place, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.place = place;
		this.userName = userName;
		this.password = password;
	}
	
	
}
