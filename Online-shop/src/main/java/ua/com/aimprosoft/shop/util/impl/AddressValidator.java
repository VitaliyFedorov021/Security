package ua.com.aimprosoft.shop.util.impl;

import java.util.List;

import ua.com.aimprosoft.shop.exceptions.IncorrectInputException;
import ua.com.aimprosoft.shop.models.Address;
import ua.com.aimprosoft.shop.util.Validator;
import ua.com.aimprosoft.shop.util.constant.ErrorConstant;


public class AddressValidator implements Validator<Address>
{
	@Override
	public void validate(final Address entity, final List<String> exceptions)
	{
		checkStreet(entity, exceptions);
		checkCountry(entity, exceptions);
		checkPostalCode(entity, exceptions);
		checkRegion(entity, exceptions);
		checkTown(entity, exceptions);
	}

	public void checkStreet(Address address, final List<String> exceptions)
	{
		String street = address.getStreet();
		if (street == null || street.isBlank())
		{
			exceptions.add(ErrorConstant.EMPTY_STREET);
		}
	}

	public void checkCountry(Address address, final List<String> exceptions)
	{
		String country = address.getCountry();
		if (country == null || country.isBlank())
		{
			exceptions.add(ErrorConstant.EMPTY_COUNTRY);
		}
	}

	public void checkPostalCode(Address address, final List<String> exceptions)
	{
		String postalCode = address.getPostalCode();
		if (postalCode == null || postalCode.isBlank())
		{
			exceptions.add(ErrorConstant.EMPTY_POSTAL_CODE);
		}
	}

	public void checkRegion(Address address, final List<String> exceptions)
	{
		String region = address.getRegion();
		if (region == null || region.isBlank())
		{
			exceptions.add(ErrorConstant.EMPTY_REGION);
		}
	}

	public void checkTown(Address address, final List<String> exceptions)
	{
		String town = address.getTown();
		if (town == null || town.isBlank())
		{
			exceptions.add(ErrorConstant.EMPTY_TOWN);
		}
	}
}
