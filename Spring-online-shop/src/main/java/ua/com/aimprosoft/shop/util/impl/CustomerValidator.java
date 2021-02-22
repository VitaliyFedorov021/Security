package ua.com.aimprosoft.shop.util.impl;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.aimprosoft.shop.entities.Customer;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


@Component
public class CustomerValidator implements Validator
{
	@Autowired
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
	public boolean supports(final Class<?> aClass)
	{
		return Customer.class.equals(aClass);
	}

	@Override
	public void validate(final Object o, final Errors errors)
	{
		final Customer customer = (Customer) o;
		checkEmail(customer, errors);
		checkPassword(customer, errors);
		checkName(customer, errors);
		checkDate(customer, errors);
		checkNumber(customer, errors);
	}

	private void checkEmail(final Customer customer, final Errors errors)
	{
		final String email = customer.getEmail();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.EMAIL, "msg.empty_email");
		if (customerService.getCustomerByEmail(customer.getEmail()).isPresent())
		{
			errors.rejectValue(ApplicationConstant.EMAIL, "msg.not_unique_email");
			return;
		}
		final Matcher matcher = emailPattern.matcher(email);
		if (!matcher.matches())
		{
			errors.rejectValue(ApplicationConstant.EMAIL, "msg.email_not_matches");
		}
	}

	private void checkPassword(final Customer customer, final Errors errors)
	{
		final String password = customer.getPassword();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.PASSWORD, "msg.empty_password");
		if (customer.getPassword().length() < 6)
		{
			errors.rejectValue(ApplicationConstant.PASSWORD, "msg.little_password");
			return;
		}
		final Matcher matcher = passwordPattern.matcher(password);
		if (!matcher.matches())
		{
			errors.rejectValue(ApplicationConstant.PASSWORD, "msg.password_not_matches");
		}
	}

	public void checkName(final Customer customer, final Errors errors)
	{
		final String firstName = customer.getFirstName();
		final String lastName = customer.getLastName();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.FIRST_NAME, "msg.empty_name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.LAST_NAME, "msg.empty_name");
		final Matcher firstMatcher = namePattern.matcher(firstName);
		final Matcher secondMatcher = namePattern.matcher(lastName);
		if (!firstMatcher.matches() || !secondMatcher.matches())
		{
			errors.rejectValue(ApplicationConstant.FIRST_NAME, "msg.name_not_matches");
		}
	}

	public void checkNumber(final Customer customer, final Errors errors)
	{
		final String number = customer.getPhoneNumber();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ApplicationConstant.PHONE_NUMBER, "msg.empty_number");
		final Matcher matcher = numberPattern.matcher(number);
		if (!matcher.matches())
		{
			errors.rejectValue(ApplicationConstant.PHONE_NUMBER, "msg.number_not_matches");
		}
	}

	public void checkDate(final Customer customer, final Errors errors)
	{
		final Date date = customer.getBirthdayDate();
		if (date == null)
		{
			errors.rejectValue(ApplicationConstant.BIRTHDAY_DATE, "msg.empty_date");
		}
	}
}
