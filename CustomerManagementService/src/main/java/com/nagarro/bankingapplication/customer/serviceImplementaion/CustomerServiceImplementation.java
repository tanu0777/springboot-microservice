package com.nagarro.bankingapplication.customer.serviceImplementaion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.bankingapplication.customer.exceptions.ResourceNotFoundException;
import com.nagarro.bankingapplication.customer.models.Customer;
import com.nagarro.bankingapplication.customer.repositories.CustomerRepository;
import com.nagarro.bankingapplication.customer.services.CustomerService;

@Component
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer saveCustomer(Customer customer) {
		boolean hasExist = customerRepository.existsById(customer.getCustomerId());
		if(!hasExist) {
			Customer newCustomer =  customerRepository.save(customer);
			return newCustomer;
		} else {
			return null;
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customerList =(List<Customer>) customerRepository.findAll();
		return customerList;
	}

	@Override
	public Customer getCustomer(Long id) {
		
		Customer customer = customerRepository.findById(id).orElseThrow(
            ()-> new ResourceNotFoundException("Customer with the id " + id + " is not available "));
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer updatedCustomer = customerRepository.save(customer);
		return updatedCustomer;
	}

	@Override
	public Customer deleteCustomer(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(
	            ()-> new ResourceNotFoundException("Customer with the given " + id + "is not available "));
		if(customer != null) {
			customerRepository.delete(customer);
		}
		return customer;
	}

}
