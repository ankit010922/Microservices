package com.dish.user.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserResponse {

	private Long userId;
	private String userName;
	private String email;
	private Boolean isApproved;
	private Boolean isActive;
	private Boolean isValidated;
	private Long roleId;
	private String roleCode;
	private LocalDateTime createdOn;
	private Boolean isDeleted;

}
