package com.eatza.customermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eatza.customermanagement.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByIdAndIsActiveTrue(Long id);

	Customer findByUserNameAndPassword(String userName, String password);
}
