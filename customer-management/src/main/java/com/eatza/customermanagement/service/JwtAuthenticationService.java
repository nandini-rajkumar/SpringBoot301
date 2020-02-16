package com.eatza.customermanagement.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eatza.customermanagement.dto.UserDto;
import com.eatza.customermanagement.exception.UnauthorizedException;
import com.eatza.customermanagement.model.Customer;
import com.eatza.customermanagement.repository.CustomerRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtAuthenticationService {


	/*@Value("${user}")
	String username;

	@Value("${password}")
	String password;*/

	@Autowired CustomerRepository customerRepository;
	
	private static final long EXPIRATIONTIME = 900000;


	public String authenticateUser(UserDto user) throws UnauthorizedException {

		Customer customer = customerRepository.findByUserNameAndPassword(user.getUsername(), user.getPassword());

		if ( null == customer) {
			log.debug("UserName/Password is invalid");
			throw new UnauthorizedException("Invalid Credentials");
		}
		/*if(!(user.getUsername().equals(username))) {
			log.debug("Username is invalid");

			throw new UnauthorizedException("Invalid Credentials");
		}
		if(!(user.getPassword().equals(password))){
			
			log.debug("Password is invalid");
			throw new UnauthorizedException("Invalid Credentials");

		}*/
		return Jwts.builder().setSubject(user.getUsername()).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).compact();



	}

}
