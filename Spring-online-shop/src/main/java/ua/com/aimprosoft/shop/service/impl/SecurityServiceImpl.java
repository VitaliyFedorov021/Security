package ua.com.aimprosoft.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ua.com.aimprosoft.shop.service.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService
{
	private final UserDetailsService userDetailsService;

	@Autowired
	public SecurityServiceImpl(final UserDetailsService userDetailsService)
	{
		this.userDetailsService = userDetailsService;
	}

	@Override
	public String getCurrentUsername()
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((UserDetails)principal).getUsername();
	}

	@Override
	public void autoLogin(final String email, final String password)
	{
		final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password);
		SecurityContextHolder.getContext().setAuthentication(token);
	}
}
