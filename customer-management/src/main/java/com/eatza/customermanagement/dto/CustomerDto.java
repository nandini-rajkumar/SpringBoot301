package com.eatza.customermanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String place;
	
	private String userName;
	private String password;
}
