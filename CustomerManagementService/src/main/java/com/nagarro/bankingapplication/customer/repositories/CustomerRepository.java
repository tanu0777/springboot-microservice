package com.nagarro.bankingapplication.customer.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.nagarro.bankingapplication.customer.models.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

}
