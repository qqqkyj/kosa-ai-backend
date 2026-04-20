package com.mycompany.backendapi.exception;

public class AccountNotExistException extends RuntimeException{
	public AccountNotExistException() {
		
	}
	
public AccountNotExistException(String message) {
		super(message);
	}
}
