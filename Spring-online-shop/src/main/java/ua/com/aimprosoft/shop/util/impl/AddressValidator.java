package ua.com.aimprosoft.shop.util.impl;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.aimprosoft.shop.forms.Address;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


@Component
public class AddressValidator implements Validator
{
	@Override
	public void validate(final Object o, final Errors errors)
	{
		final Address address = (Address) o;
		checkStreet(address, errors);
		checkCountry(address, errors);
		checkPostalCode(address, errors);
		checkRegion(address, errors);
		checkTown(address, errors);
	}

	public void checkStreet(final Address address, final Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.STREET, "msg.empty_street");
	}

	public void checkCountry(final Address address, final Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.COUNTRY, "msg.empty_country");
	}

	public void checkPostalCode(final Address address, final Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.POSTAL_CODE, "msg.empty_postal_code");
	}

	public void checkRegion(final Address address, final Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.REGION, "msg.empty_region");
	}

	public void checkTown(final Address address, final Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.TOWN, "msg.empty_town");
	}

	@Override
	public boolean supports(final Class<?> aClass)
	{
		return Address.class.equals(aClass);
	}
}
