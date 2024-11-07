package com.nagarro.bankingapplication.customer.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@NotEmpty(message = "Customer name is required")
	private String customerName;
	@NotEmpty(message = "Email is required ")
	@Email
	private String email;
	@NotNull
	private Date dob;


}
