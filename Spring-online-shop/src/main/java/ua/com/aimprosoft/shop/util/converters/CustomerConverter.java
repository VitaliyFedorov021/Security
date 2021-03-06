package ua.com.aimprosoft.shop.util.converters;

import ua.com.aimprosoft.shop.dto.CustomerDto;
import ua.com.aimprosoft.shop.entities.Customer;
import ua.com.aimprosoft.shop.forms.CustomerForm;


public class CustomerConverter
{
	public static CustomerDto formToDto(final CustomerForm customer)
	{
		final CustomerDto dto = new CustomerDto();
		dto.setEmail(customer.getEmail());
		dto.setBirthdayDate(customer.getBirthdayDate());
		dto.setFirstName(customer.getFirstName());
		dto.setLastName(customer.getLastName());
		dto.setGender(customer.getGender());
		dto.setPassword(customer.getPassword());
		dto.setPhoneNumber(customer.getPhoneNumber());
		return dto;
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
		dto.setBirthdayDate(customer.getBirthdayDate());
		return dto;
	}

	public static Customer dtoToEntity(final CustomerDto customerDto)
	{
		final Customer customer = new Customer();
		customer.setId(customerDto.getId());
		customer.setEmail(customerDto.getEmail());
		customer.setBirthdayDate(customerDto.getBirthdayDate());
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setGender(customerDto.getGender());
		customer.setPassword(customerDto.getPassword());
		customer.setPhoneNumber(customerDto.getPhoneNumber());
		return customer;
	}
}
