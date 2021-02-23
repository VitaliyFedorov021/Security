package ua.com.aimprosoft.shop.security;

import java.util.HashSet;

import org.springframework.security.core.userdetails.User;

import ua.com.aimprosoft.shop.dto.CustomerDto;


public class SecurityCustomer extends User
{
	private final CustomerDto customerDto;

	public SecurityCustomer(final CustomerDto customerDto)
	{
		super(customerDto.getEmail(), customerDto.getPassword(), new HashSet<>());
		this.customerDto = customerDto;
	}

	public CustomerDto getCustomer()
	{
		return customerDto;
	}
}
