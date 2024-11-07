package com.nagarro.bankingapplication.account.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nagarro.bankingapplication.account.constants.ACTION;
import static com.nagarro.bankingapplication.account.constants.Constants.*;
import com.nagarro.bankingapplication.account.models.Account;
import com.nagarro.bankingapplication.account.models.Customer;
import com.nagarro.bankingapplication.account.models.CustomerDetails;
import com.nagarro.bankingapplication.account.services.AccountService;
import com.nagarro.bankingapplication.account.utils.WithdrawDepositInput;


@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AccountService accountService;
	
	public Customer getCustomer(long customerId) {
		Customer customer = restTemplate.getForObject("http://customer-management-service/customer/id/" + customerId,
				Customer.class);
		return customer;
	}
	
	/* Method to add new account */
	@PostMapping("/add")
	public ResponseEntity<?> saveAccount(@RequestBody Account account) {
		Account newAccount = accountService.saveAccount(account);
		if(newAccount != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Account already exists ");
		}
	}

	/* Method to get the details of all accounts */
	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> allAccounts = accountService.getAllAccounts();
		if (allAccounts != null && allAccounts.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(allAccounts);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}
	
	/*
	 * Method to get details of the account with the given account number with
	 * related customer details
	 */
	@GetMapping("/accountnumber/{accountNumber}")
	public ResponseEntity<CustomerDetails> getAccountDetail(@PathVariable("accountNumber") String id) {

		Account account = accountService.getAccount(id);
		Customer customer = this.getCustomer(account.getCustomerId());
		CustomerDetails allDetail = new CustomerDetails();
		allDetail.setAccount(account);
		allDetail.setCustomer(customer);

		return ResponseEntity.ok(allDetail);
	}

	@PostMapping("/withdraw")
	public ResponseEntity<?> withdraw(@Valid @RequestBody WithdrawDepositInput withdrawInput) {
		Account account = withdrawInput.getAccount();
		if (account == null) {
			return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.OK);
		} else {
			Customer availablecustomer = withdrawInput.getCustomer();
			Customer registeredCustomer = this.getCustomer(availablecustomer.getCustomerId());
			if (accountService.isCustomerValid(availablecustomer, registeredCustomer)) {
				if (accountService.isAmountAvailable(withdrawInput.getAmount(), account.getCurrentBalance())) {
					accountService.updateAccountBalance(account, withdrawInput.getAmount(), ACTION.WITHDRAW);
					return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(INSUFFICIENT_ACCOUNT_BALANCE, HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<>(INVALID_CUSTOMER, HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@PostMapping("/deposit")
	public ResponseEntity<?> deposit(@Valid @RequestBody WithdrawDepositInput depositInput) {
		Account account = depositInput.getAccount();
		if (account == null) {
			return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.OK);
		} else {
			Customer availablecustomer = depositInput.getCustomer();
			Customer registeredCustomer = this.getCustomer(availablecustomer.getCustomerId());
			if (accountService.isCustomerValid(availablecustomer,registeredCustomer)) {
				accountService.updateAccountBalance(account, depositInput.getAmount(), ACTION.DEPOSIT);
				return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(INVALID_CUSTOMER, HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@DeleteMapping("/accountnumber/{accountNumber}")
	public ResponseEntity<?> deletebyAccountNumber(@PathVariable("accountNumber") String accountNumber) {
		accountService.deleteAccountByAccountNumber(accountNumber);
		return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
	}
	
	@DeleteMapping("/customerId/{customerId}")
	public ResponseEntity<?> deletebyCustomerId(@PathVariable("customerId") long id) {
	    accountService.deleteAccountByCustomerId(id);
	   return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
	}

}
