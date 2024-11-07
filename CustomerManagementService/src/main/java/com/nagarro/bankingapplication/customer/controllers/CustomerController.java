package com.nagarro.bankingapplication.customer.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nagarro.bankingapplication.customer.models.Customer;
import com.nagarro.bankingapplication.customer.repositories.CustomerRepository;
import com.nagarro.bankingapplication.customer.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerRepository customerRepository;
	
	/* Method to add new customer */
	@PostMapping("/add")
	public ResponseEntity<?> saveCustomer(@Valid @RequestBody Customer customer) {
		
		Customer newCustomer = customerService.saveCustomer(customer);
		if(newCustomer != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this id already exists ");
		}	
	}
	
	/* Method to get details of all the customers */
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> allCustomers = customerService.getAllCustomers();
		if(allCustomers != null && allCustomers.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(allCustomers);
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}
	
	/* Method to get details of the customer with the given customer id */
	@GetMapping("/id/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {	
		Customer customer = customerService.getCustomer(id);
		return ResponseEntity.ok(customer);	
	}
	
	
	
	/* Method to update the customer */
	@PutMapping("/id/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id ,@Valid @RequestBody  Customer customer) {
		if(customer.getCustomerId() != id) {
			customer.setCustomerId(id);
			//return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this id already exists ");
		} 
		Customer updateCustomer = customerService.updateCustomer(customer);
		return ResponseEntity.ok(updateCustomer);
	}
	
	/* Method to delete the customer with the given customer id and delete the account details of the customer*/
	@DeleteMapping("/id/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id) {
		Customer customer = customerService.deleteCustomer(id);
		restTemplate.delete("http://account-management-service/account/customerId/" + customer.getCustomerId());
		return ResponseEntity.ok(customer);
	}
}

