package com.dish.user.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OtpRequestForm {

	@NotBlank(message = "Transaction ID can not be blank.")
	private String transactionId;
	
	@NotBlank(message = "OTP can not be empty")
	@Size(min = 6, max = 6,message = "OTP length should be equal to 6 digits.")
	private Integer otp;
	
	@NotBlank(message = "User Id can not be empty")
	private Long userId;
}
