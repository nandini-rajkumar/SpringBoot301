package com.eatza.customermanagement.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eatza.customermanagement.dto.CustomerDto;
import com.eatza.customermanagement.exception.CustomerNotFoundException;
import com.eatza.customermanagement.model.Customer;
import com.eatza.customermanagement.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(CustomerDto customerDto) {
		log.debug("In addCustomer method, creating customer object to persist");
		Customer customer = new Customer(customerDto.getFirstName(), customerDto.getLastName(), customerDto.getPlace(),
				customerDto.getUserName(), customerDto.getPassword());
		log.debug("saving customer in db");
		Customer savedCustomer = customerRepository.save(customer);

		log.debug("Saved customer to db");
		return savedCustomer;
	}

	@Override
	public Customer updateCustomer(CustomerDto customerDto) throws CustomerNotFoundException {
		Customer customer = customerRepository.findByIdAndIsActiveTrue(customerDto.getId());

		if (customer != null) {
			customer.setFirstName(customerDto.getFirstName());
			customer.setLastName(customerDto.getLastName());
			customer.setPlace(customerDto.getPlace());
			customer.setUserName(customerDto.getUserName());
			customer.setPassword(customerDto.getPassword());
			customer.setUpdateDateTime(LocalDateTime.now());
			return customerRepository.save(customer);
		} else {
			throw new CustomerNotFoundException("Update Failed, respective customer not found");
		}
	}

	@Override
	public void deactivateCustomer(Long customerId) throws CustomerNotFoundException {
		Customer customer = customerRepository.findByIdAndIsActiveTrue(customerId);

		if (customer != null) {
			customer.setActive(false);
			customer.setUpdateDateTime(LocalDateTime.now());
			customerRepository.save(customer);
		} else {
			throw new CustomerNotFoundException("Update Failed, respective customer not found");
		}
	}

	@Override
	public Optional<Customer> getOrderById(Long customerId) {
		return customerRepository.findById(customerId);
	}

}
