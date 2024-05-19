package com.learning.all.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthendicateSucessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		System.err.println("_____________________________________________________________________________________");
		boolean isPrincipal = authentication.getAuthorities().stream()
				.anyMatch(role -> role.getAuthority().equals("ROLE_PRINCIPAL"));

		boolean isTachecher = authentication.getAuthorities().stream()
				.anyMatch(role -> role.getAuthority().equals("ROLE_TEACHER"));

		if (isPrincipal) {
			System.out.println("Raj ----> " + authentication.getAuthorities());

			setDefaultTargetUrl("/principal/home");

		} else if (isTachecher) {
			setDefaultTargetUrl("/teacher/home");

		} else {
			setDefaultTargetUrl("/student/home");
		}

		super.onAuthenticationSuccess(request, response, authentication);
	}

}
