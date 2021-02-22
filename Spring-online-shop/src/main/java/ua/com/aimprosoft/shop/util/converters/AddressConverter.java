package ua.com.aimprosoft.shop.util.converters;

import ua.com.aimprosoft.shop.entities.Address;


public class AddressConverter
{
	public static Address formToEntity(final ua.com.aimprosoft.shop.forms.Address address)
	{
		final Address entity = new Address();
		entity.setTown(address.getTown());
		entity.setCountry(address.getCountry());
		entity.setPostalCode(address.getPostalCode());
		entity.setRegion(address.getRegion());
		entity.setStreet(address.getStreet());
		return entity;
	}
	
	public static ua.com.aimprosoft.shop.dto.Address entityToDto(final Address address)
	{
		final ua.com.aimprosoft.shop.dto.Address dto = new ua.com.aimprosoft.shop.dto.Address();
		dto.setTown(address.getTown());
		dto.setCountry(address.getCountry());
		dto.setPostalCode(address.getPostalCode());
		dto.setRegion(address.getRegion());
		dto.setStreet(address.getStreet());
		return dto;
	}
}
