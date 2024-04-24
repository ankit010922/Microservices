package com.dish.api.gateway.exception;

public class UnAuthorizedExc extends RuntimeException{

private static final long serialVersionUID = 1L;
	
	public UnAuthorizedExc(String msg)
	{
		super(msg);
	}
	
}
