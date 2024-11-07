package com.nagarro.bankingapplication.account.utils;

import com.nagarro.bankingapplication.account.models.Account;
import com.nagarro.bankingapplication.account.models.Customer;
import com.nagarro.bankingapplication.account.models.CustomerDetails;

public class WithdrawDepositInput extends CustomerDetails {
	
	private double amount;
	private Customer customer;
	private Account account;
	
	
	
	public WithdrawDepositInput() {
		super();
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
}