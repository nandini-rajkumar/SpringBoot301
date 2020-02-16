package com.eatza.customermanagement.service;

import java.util.Optional;

import com.eatza.customermanagement.dto.CustomerDto;
import com.eatza.customermanagement.exception.CustomerNotFoundException;
import com.eatza.customermanagement.model.Customer;

public interface CustomerService {

	Customer addCustomer(CustomerDto reviewDto);

	Customer updateCustomer(CustomerDto customerDto) throws CustomerNotFoundException;

	void deactivateCustomer(Long customerId) throws CustomerNotFoundException;

	Optional<Customer> getOrderById(Long customerId);

}
