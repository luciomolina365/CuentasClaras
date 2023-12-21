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

@WebFilter(filterName = "jwt-auth-filter", urlPatterns = "/test")  ////   "/*"
public class JWTAuthenticationFilter implements Filter {

	private static final String AUTH_PATH = "/user/create";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		if (shouldSkipFilter(req.getRequestURI())) {
			chain.doFilter(request, response);
			return;
		}

		System.out.println("ENTRO AL FILTRO");

		String token = req.getHeader(HttpHeaders.AUTHORIZATION);
		if (token == null || !TokenServices.validateToken(token)) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.setStatus(HttpStatus.FORBIDDEN.value());
			return;
		}
		chain.doFilter(request, response);
	}

	private boolean shouldSkipFilter(String requestURI) {
		return AUTH_PATH.equals(requestURI) || HttpMethod.OPTIONS.matches(requestURI);
	}

}
