package com.nagarro.bankingapplication.account.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	
	@Id
	private String accountNumber;
	private double currentBalance;
	private long customerId;

}
