package ua.com.aimprosoft.shop.util.converters;

import ua.com.aimprosoft.shop.dto.AddressDto;
import ua.com.aimprosoft.shop.entities.Address;
import ua.com.aimprosoft.shop.forms.AddressForm;


public class AddressConverter
{
	public static AddressDto formToDto(final AddressForm address)
	{
		final AddressDto dto = new AddressDto();
		dto.setTown(address.getTown());
		dto.setCountry(address.getCountry());
		dto.setPostalCode(address.getPostalCode());
		dto.setRegion(address.getRegion());
		dto.setStreet(address.getStreet());
		return dto;
	}

	public static Address dtoToEntity(final AddressDto addressDto)
	{
		final Address address = new Address();
		address.setTown(addressDto.getTown());
		address.setCountry(addressDto.getCountry());
		address.setPostalCode(addressDto.getPostalCode());
		address.setRegion(addressDto.getRegion());
		address.setStreet(addressDto.getStreet());
		return address;
	}
}
