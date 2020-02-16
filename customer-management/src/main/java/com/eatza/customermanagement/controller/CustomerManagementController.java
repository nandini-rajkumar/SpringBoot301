package com.eatza.customermanagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.customermanagement.dto.CustomerDto;
import com.eatza.customermanagement.exception.CustomerNotFoundException;
import com.eatza.customermanagement.model.Customer;
import com.eatza.customermanagement.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CustomerManagementController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDto reviewDto)
			throws CustomerNotFoundException {
		log.debug("In registerCustomer method, calling the service");
		Customer order = customerService.addCustomer(reviewDto);
		log.debug("Added customer Successfully");
		return ResponseEntity.status(HttpStatus.OK).body(order);
	}

	@PutMapping("/customer/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@RequestHeader String authorization,
			@RequestBody CustomerDto customerDto, @PathVariable Long customerId) throws CustomerNotFoundException {

		log.debug(" In updateCustomer method, calling service");
		Customer customer = customerService.updateCustomer(customerDto);
		log.debug("Returning back the object");

		return ResponseEntity.status(HttpStatus.OK).body(customer);

	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> getOrderById(@RequestHeader String authorization, @PathVariable Long orderId) throws CustomerNotFoundException{
		log.debug("In get customer by id method, calling service to get customer by ID");
		Optional<Customer> order = customerService.getOrderById(orderId);
		if(order.isPresent()) {
			log.debug("Got customer from service");
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(order.get());
		}
		else {
			log.debug("No customer found");
			throw new CustomerNotFoundException("No result found for specified inputs");
		}
	}

	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<?> deactivateCustomer(@RequestHeader String authorization, @PathVariable Long customerId)
			throws CustomerNotFoundException {

		log.debug(" In updateCustomer method, calling service");
		customerService.deactivateCustomer(customerId);
		log.debug("Returning back the object");

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}
