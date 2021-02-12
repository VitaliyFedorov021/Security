package ua.com.aimprosoft.shop.util.impl;

import java.util.List;

import ua.com.aimprosoft.shop.exceptions.IncorrectInputException;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;
import ua.com.aimprosoft.shop.models.Address;
import ua.com.aimprosoft.shop.util.Validator;
import ua.com.aimprosoft.shop.util.constant.ErrorConstant;


public class AddressValidator implements Validator<Address>
{
	@Override
	public void validate(final Address entity, final List<Exception> exceptions)
	{
		checkStreet(entity, exceptions);
		checkCountry(entity, exceptions);
		checkPostalCode(entity, exceptions);
		checkRegion(entity, exceptions);
		checkTown(entity, exceptions);
	}

	public void checkStreet(Address address, List<Exception> exceptions) {
		String street = address.getStreet();
		if (street == null || street.isBlank())
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_STREET));
		}
	}

	public void checkCountry(Address address, List<Exception> exceptions) {
		String country = address.getCountry();
		if (country == null || country.isBlank()) {
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_COUNTRY));
		}
	}

	public void checkPostalCode(Address address, List<Exception> exceptions) {
		String postalCode = address.getPostalCode();
		if (postalCode == null || postalCode.isBlank()) {
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_POSTAL_CODE));
		}
	}
	public void checkRegion(Address address, List<Exception> exceptions) {
		String region = address.getRegion();
		if (region == null || region.isBlank()) {
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_REGION));
		}
	}

	public void checkTown(Address address, List<Exception> exceptions) {
		String town = address.getTown();
		if (town == null || town.isBlank()) {
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_TOWN));
		}
	}
}
