package com.nagarro.bankingapplication.account.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {
	
	private Account account;
	private Customer customer;
	
}
	
	
	
	
