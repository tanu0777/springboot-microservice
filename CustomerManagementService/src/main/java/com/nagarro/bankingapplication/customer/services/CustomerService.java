package com.nagarro.bankingapplication.customer.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.nagarro.bankingapplication.customer.models.Customer;

@Service
public interface CustomerService {
	
	// add customer
	Customer saveCustomer(Customer customer);
	
	// fetch all customers
	List<Customer> getAllCustomers();
	
	// fetch customer by id
	Customer getCustomer(Long id);
	
	// update customer
	Customer updateCustomer(Customer customer);
	
	// delete customer
	Customer deleteCustomer(Long id);

}
