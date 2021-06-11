package com.finalproject.backend.service;

import com.finalproject.backend.security.services.UserDetailsImpl;

import org.springframework.security.core.context.SecurityContextHolder;

public class TokenHolder {
    public String getIdUserFromToken() {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return  userDetails.getId();
	}
}
