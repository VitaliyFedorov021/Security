package ua.com.aimprosoft.shop.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import ua.com.aimprosoft.shop.models.Customer;


public class SecurityCustomer implements UserDetails
{
	private final String username;
	private final String password;

	public SecurityCustomer(final String username, final String password)
	{
		this.username = username;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return null;
	}

	@Override
	public String getPassword()
	{
		return password;
	}

	@Override
	public String getUsername()
	{
		return username;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return false;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return false;
	}

	@Override
	public boolean isEnabled()
	{
		return false;
	}

	public static UserDetails fromCustomer(Customer customer)
	{
		return new User(customer.getEmail(), customer.getPassword(), new HashSet<>());
	}
}
