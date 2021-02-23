package ua.com.aimprosoft.shop.util.converters;

import ua.com.aimprosoft.shop.entities.Address;
import ua.com.aimprosoft.shop.forms.AddressForm;


public class AddressConverter
{
	public static Address formToEntity(final AddressForm address)
	{
		final Address entity = new Address();
		entity.setTown(address.getTown());
		entity.setCountry(address.getCountry());
		entity.setPostalCode(address.getPostalCode());
		entity.setRegion(address.getRegion());
		entity.setStreet(address.getStreet());
		return entity;
	}
}
