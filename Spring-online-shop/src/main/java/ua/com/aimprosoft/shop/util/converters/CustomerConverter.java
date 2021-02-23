package ua.com.aimprosoft.shop.util.converters;

import ua.com.aimprosoft.shop.dto.CustomerDto;
import ua.com.aimprosoft.shop.entities.Customer;
import ua.com.aimprosoft.shop.forms.CustomerForm;


public class CustomerConverter
{
	public static Customer formToEntity(final CustomerForm customer)
	{
		final Customer entity = new Customer();
		entity.setEmail(customer.getEmail());
		entity.setBirthdayDate(customer.getBirthdayDate());
		entity.setFirstName(customer.getFirstName());
		entity.setLastName(customer.getLastName());
		entity.setGender(customer.getGender());
		entity.setPassword(customer.getPassword());
		entity.setPhoneNumber(customer.getPhoneNumber());
		return entity;
	}

	public static CustomerDto entityToDto(final Customer customer)
	{
		final CustomerDto dto = new CustomerDto();
		dto.setId(customer.getId());
		dto.setEmail(customer.getEmail());
		dto.setPassword(customer.getPassword());
		dto.setFirstName(customer.getFirstName());
		dto.setLastName(customer.getLastName());
		dto.setPhoneNumber(customer.getPhoneNumber());
		return dto;
	}

	public static Customer dtoToEntity(final CustomerDto customerDto)
	{
		final Customer customer = new Customer();
		customer.setId(customerDto.getId());
		customer.setEmail(customerDto.getEmail());
		return customer;
	}
}
