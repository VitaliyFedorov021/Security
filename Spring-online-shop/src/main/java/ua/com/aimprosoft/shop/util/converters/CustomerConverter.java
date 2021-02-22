package ua.com.aimprosoft.shop.util.converters;

import ua.com.aimprosoft.shop.entities.Customer;


public class CustomerConverter
{
	public static Customer formToEntity(final ua.com.aimprosoft.shop.forms.Customer customer)
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

	public static ua.com.aimprosoft.shop.dto.Customer entityToDto(final Customer customer)
	{
		final ua.com.aimprosoft.shop.dto.Customer dto = new ua.com.aimprosoft.shop.dto.Customer();
		dto.setFirstName(customer.getFirstName());
		dto.setLastName(customer.getLastName());
		dto.setPhoneNumber(customer.getPhoneNumber());
		return dto;
	}
}
