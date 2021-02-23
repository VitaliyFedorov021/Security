package ua.com.aimprosoft.shop.util.impl;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.aimprosoft.shop.forms.AddressForm;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


@Component
public class AddressValidator implements Validator
{
	@Override
	public void validate(final Object o, final Errors errors)
	{
		final AddressForm address = (AddressForm) o;
		checkStreet(address, errors);
		checkCountry(address, errors);
		checkPostalCode(address, errors);
		checkRegion(address, errors);
		checkTown(address, errors);
	}

	public void checkStreet(final AddressForm address, final Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.STREET, "msg.empty_street");
	}

	public void checkCountry(final AddressForm address, final Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.COUNTRY, "msg.empty_country");
	}

	public void checkPostalCode(final AddressForm address, final Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.POSTAL_CODE, "msg.empty_postal_code");
	}

	public void checkRegion(final AddressForm address, final Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.REGION, "msg.empty_region");
	}

	public void checkTown(final AddressForm address, final Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.TOWN, "msg.empty_town");
	}

	@Override
	public boolean supports(final Class<?> aClass)
	{
		return AddressForm.class.equals(aClass);
	}
}
