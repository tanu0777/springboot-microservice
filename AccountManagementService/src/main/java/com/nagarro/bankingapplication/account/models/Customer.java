package com.nagarro.bankingapplication.account.models;

import java.sql.Date;

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
public class Customer {

	@Id
	private Long customerId;
	private String customerName;
	private String email;
	private Date dob;
	
}
