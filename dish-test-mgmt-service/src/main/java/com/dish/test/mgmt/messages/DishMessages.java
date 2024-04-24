package com.dish.test.mgmt.messages;

public interface DishMessages {
	public static final String EMAIL_VALID = "Email Id is valid";
	public static final String EMAIL_INVALID = "Email Id is invalid";
	public static final String EMAIL_NOTINUSE = "Email Id is not in use";	
	public static final String EMAIL_ALREADYINUSE = "Email Id is already in use";
	public static final String PASSWORD_NOT_MATCH_CRITERIA = "Password is not matching criteria (1 capital,1 small, minimum 8 character)";
	public static final String PASSWORD_MATCH_CRITERIA = "Password is matching criteria (1 capital,1 small, minimum 8 character)";
	public static final String USER_SAVED = "User saved";
	public static final String USER_NOT_SAVED = "User not saved";
	public static final String USER_NOT_EXIST = "User doesnot exist by given ID";
	
	
}
