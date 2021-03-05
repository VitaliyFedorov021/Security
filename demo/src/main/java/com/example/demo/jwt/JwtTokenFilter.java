package com.example.demo.jwt;

import java.io.IOException;

import javax.persistence.Access;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;


@Component
public class JwtTokenFilter extends GenericFilterBean
{
	private final JwtTokenProvider tokenProvider;

	@Autowired
	public JwtTokenFilter(final JwtTokenProvider tokenProvider)
	{
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
			final FilterChain filterChain)
			throws IOException, ServletException
	{
		String token = tokenProvider.resolveToken((HttpServletRequest) servletRequest);
		if (token != null && tokenProvider.validateToken(token)) {
			Authentication authentication = tokenProvider.getAuthentication(token);
			if (authentication != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
}
