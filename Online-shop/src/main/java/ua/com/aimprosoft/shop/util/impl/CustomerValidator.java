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
	private final Pattern emailPattern = Pattern.compile(ApplicationConstant.EMAIL_PATTERN);
	private final Pattern passwordPattern = Pattern.compile(ApplicationConstant.PASSWORD_PATTERN);
	private final Pattern namePattern = Pattern.compile(ApplicationConstant.NAME_PATTERN);
	private final Pattern numberPattern = Pattern.compile(ApplicationConstant.NUMBER_PATTERN);

	public CustomerValidator(final CustomerService customerService)
	{
		this.customerService = customerService;
	}

	@Override
	public void validate(final Customer entity, final List<Exception> exceptions)
	{
		checkEmail(entity, exceptions);
		checkPassword(entity, exceptions);
		checkDate(entity, exceptions);
		checkName(entity, exceptions);
		checkNumber(entity, exceptions);
	}

	public void checkEmail(final Customer customer, final List<Exception> exceptions)
	{
		final String email = customer.getEmail();
		if (email == null || email.isEmpty())
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_EMAIL));
			return;
		}
		final Matcher matcher = emailPattern.matcher(email);
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

	public void checkPassword(final Customer customer, final List<Exception> exceptions)
	{
		final String password = customer.getPassword();
		if (password == null || password.isEmpty())
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_PASSWORD));
			return;
		}
		final Matcher matcher = passwordPattern.matcher(password);
		if (!matcher.matches())
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.PASSWORD_NOT_MATCHES));
		}
	}

	public void checkName(final Customer customer, final List<Exception> exceptions)
	{
		final String firstName = customer.getFirstName();
		final String lastName = customer.getLastName();
		if (firstName == null || lastName == null)
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_NAME));
			return;
		}
		final Matcher firstMatcher = namePattern.matcher(firstName);
		final Matcher secondMatcher = namePattern.matcher(lastName);
		if (!firstMatcher.matches() || !secondMatcher.matches())
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.NAME_NOT_MATCHES));
		}
	}

	public void checkNumber(final Customer customer, final List<Exception> exceptions)
	{
		final String number = customer.getPhoneNumber();
		if (number == null)
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_NUMBER));
			return;
		}
		final Matcher matcher = numberPattern.matcher(number);
		if (!matcher.matches())
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.NUMBER_NOT_MATCHES));
		}
	}

	public void checkDate(final Customer customer, final List<Exception> exceptions)
	{
		final Date date = customer.getBirthdayDate();
		if (date == null)
		{
			exceptions.add(new IncorrectInputException(ErrorConstant.EMPTY_DATE));
		}
	}
}
