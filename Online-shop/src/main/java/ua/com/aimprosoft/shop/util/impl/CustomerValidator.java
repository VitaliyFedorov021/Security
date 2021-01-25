package ua.com.aimprosoft.shop.util.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.com.aimprosoft.shop.exceptions.IncorrectInputException;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.util.Validator;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.constant.ErrorConstant;


public class CustomerValidator implements Validator<Customer>
{
	private final CustomerService customerService;

	public CustomerValidator(final CustomerService customerService)
	{
		this.customerService = customerService;
	}

	@Override
	public void validate(final Customer entity, final List<Exception> exceptions)
	{
		isEmailValid(entity, exceptions);
		isPasswordValid(entity, exceptions);
		isDateValid(entity, exceptions);
		isNameValid(entity, exceptions);
		isNumberValid(entity, exceptions);
	}

	public void isEmailValid(final Customer customer, final List<Exception> exceptions)
	{
		final String email = customer.getEmail();
		if (email == null || email.isEmpty())
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_EMAIL));
			return;
		}
		final Pattern pattern = Pattern.compile(ApplicationConstant.EMAIL_PATTERN);
		final Matcher matcher = pattern.matcher(email);
		if (!matcher.matches())
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.EMAIL_NOT_MATCHES));
			return;
		}
		final Optional<Customer> optionalCustomer = customerService.getCustomerByEmail(email);
		if (optionalCustomer.isPresent())
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.NOT_UNIQUE_EMAIL));
		}
	}

	public void isPasswordValid(final Customer customer, final List<Exception> exceptions)
	{
		final String password = customer.getPassword();
		if (password == null || password.isEmpty())
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_PASSWORD));
			return;
		}
		final Pattern pattern = Pattern.compile(ApplicationConstant.PASSWORD_PATTERN);
		final Matcher matcher = pattern.matcher(password);
		if (!matcher.matches())
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.PASSWORD_NOT_MATCHES));
		}
	}

	public void isNameValid(final Customer customer, final List<Exception> exceptions)
	{
		final String firstName = customer.getFirstName();
		final String lastName = customer.getLastName();
		if (firstName == null || lastName == null)
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_NAME));
			return;
		}
		final Pattern pattern = Pattern.compile(ApplicationConstant.NAME_PATTERN);
		final Matcher firstMatcher = pattern.matcher(firstName);
		final Matcher secondMatcher = pattern.matcher(lastName);
		if (!firstMatcher.matches() || !secondMatcher.matches())
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.NAME_NOT_MATCHES));
		}
	}

	public void isNumberValid(final Customer customer, final List<Exception> exceptions)
	{
		final String number = customer.getPhoneNumber();
		if (number == null)
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_NUMBER));
			return;
		}
		final Pattern pattern = Pattern.compile(ApplicationConstant.NUMBER_PATTERN);
		final Matcher matcher = pattern.matcher(number);
		if (!matcher.matches())
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.NUMBER_NOT_MATCHES));
		}
	}

	public void isDateValid(final Customer customer, final List<Exception> exceptions)
	{
		final Date date = customer.getBirthdayDate();
		if (date == null)
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_DATE));
		}
	}
}
