package com.example.demo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class MySuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		List<SimpleGrantedAuthority> sga=(List<SimpleGrantedAuthority>)authentication.getAuthorities();
		for(SimpleGrantedAuthority s:sga) {
			System.out.println(s.toString());
		}
		if(sga.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			System.out.println("Authority Found");
			response.sendRedirect("/home");
		}
		else if(sga.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			response.sendRedirect("/adminHome");
		}
		
	}

}
