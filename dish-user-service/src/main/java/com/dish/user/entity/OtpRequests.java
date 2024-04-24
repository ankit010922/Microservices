package com.dish.user.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class OtpRequests {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long otpRequestId;
	
	@Column
	private String transactionId;
	
	@Column
	private Long userId;
	
	@Column
	private Integer otp;
}
