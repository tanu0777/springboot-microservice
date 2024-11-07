package com.nagarro.bankingapplication.account.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.bankingapplication.account.constants.ACTION;
import com.nagarro.bankingapplication.account.models.Account;
import com.nagarro.bankingapplication.account.models.Customer;

@Service
public interface AccountService {
	
	    // add account
	    public Account  saveAccount(Account account);
	    
	    // fetch all accounts
	    public List<Account> getAllAccounts();
	    
		// fetch account by accountNumber
        public Account getAccount(String accountNumber);
		
		// delete account by account number
		public void deleteAccountByAccountNumber(String accountNumber);
		
		// delete account by customer Id
		public void deleteAccountByCustomerId(Long customerId);
		
		// check current balance
		public boolean isAmountAvailable(double amount,double currentBalance);
		
		// update balance
		public void updateAccountBalance(Account account, double amount, ACTION action);
		
		public boolean isCustomerValid(Customer customer1, Customer customer2);
		
}
