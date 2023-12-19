package com.ttps2023.CuentasClaras.filter;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.ttps2023.CuentasClaras.services.TokenServices;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(filterName="jwt-auth-filter",urlPatterns="*")
public class JWTAuthenticationFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		// El login del usuarios es publico
		if ("/auth".equals(req.getRequestURI()) || HttpMethod.OPTIONS.matches(req.getMethod())) {
			chain.doFilter(request, response);
			return;
		}
		String token = req.getHeader(HttpHeaders.AUTHORIZATION);
		if (token == null || !TokenServices.validateToken(token)) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.setStatus(HttpStatus.FORBIDDEN.value());
			return;
		}
		chain.doFilter(request, response);
	}

}
