package com.nagarro.bankingapplication.account.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.nagarro.bankingapplication.account.models.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

	public Account findByCustomerId(Long id);
}
