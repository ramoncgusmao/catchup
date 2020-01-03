package com.ramon.catchup.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.ramon.catchup.security.UserSS;


public class UserService {

	public static UserSS authenticate() {
		try {
			
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
