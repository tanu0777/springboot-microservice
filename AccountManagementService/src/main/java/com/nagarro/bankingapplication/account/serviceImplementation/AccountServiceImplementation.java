package com.nagarro.bankingapplication.account.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.bankingapplication.account.models.Account;
import com.nagarro.bankingapplication.account.models.Customer;
import com.nagarro.bankingapplication.account.repositories.AccountRepository;
import com.nagarro.bankingapplication.account.services.AccountService;
import com.nagarro.bankingapplication.account.constants.ACTION;
import com.nagarro.bankingapplication.account.exceptions.ResourceNotFoundException;

@Component
public class AccountServiceImplementation implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Account saveAccount(Account account) {
		boolean hasExist = accountRepository.existsById(account.getAccountNumber());
		if (!hasExist) {
			Account newAccount = accountRepository.save(account);
			return newAccount;
		} else {
			return null;
		}
	}
	
	@Override
	public Account getAccount(String accountNumber) {
		Account account = accountRepository.findById(accountNumber).orElseThrow(
				() -> new ResourceNotFoundException("Account with " + accountNumber + " is not available "));
		return account;
	}

	@Override
	public void deleteAccountByAccountNumber(String accountNumber) {
		Account account = accountRepository.findById(accountNumber).orElseThrow(
				() -> new ResourceNotFoundException("Account with " + accountNumber + " is not available "));
		if(account != null) {
			accountRepository.delete(account);
		}
		
	}
	
	@Override
	public void deleteAccountByCustomerId(Long customerId) {
		Account account = accountRepository.findByCustomerId(customerId);
		if(account != null) {
			accountRepository.delete(account);
		}
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> allAccounts = (List<Account>) accountRepository.findAll();
		return allAccounts;
	}
	
	@Override
	public void updateAccountBalance(Account account, double amount, ACTION action) {
        if (action == ACTION.WITHDRAW) {
        		account.setCurrentBalance((account.getCurrentBalance() - amount));
        } else if (action == ACTION.DEPOSIT) {
            account.setCurrentBalance((account.getCurrentBalance() + amount));
        }
        accountRepository.save(account);
    }

	@Override
	public boolean isAmountAvailable(double amount, double accountBalance) {
        return (accountBalance - amount) >= 0;
    }
	
	@Override
	public boolean isCustomerValid(Customer customer1, Customer customer2) {

		if (customer1.getCustomerId() == customer2.getCustomerId()
				&& customer1.getCustomerName().equals((customer2.getCustomerName()))
				&& customer1.getEmail().equals((customer2.getEmail()))
				&& customer1.getDob().equals((customer2.getDob()))) {
			return true;
		} else {
			return false;
		}

	}
}
