package com.centit.sys.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class SimpleUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5229279936142650176L;
	private String token;
	
	
	public String getToken() {
		return token;
	}


	public SimpleUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}


	public SimpleUsernamePasswordAuthenticationToken(Object principal, Object credentials, String token) {
		super(principal, credentials);
		this.token = token;
	}
	
	
}
