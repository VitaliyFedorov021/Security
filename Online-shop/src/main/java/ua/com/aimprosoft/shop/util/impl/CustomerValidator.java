package ua.com.aimprosoft.shop.util.impl;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.util.Validator;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.constant.ErrorConstant;


public class CustomerValidator implements Validator<Customer>
{
	private Pattern pattern;
	private final HttpServletRequest request;
	private final CustomerService customerService;

	public CustomerValidator(final HttpServletRequest request, final CustomerService customerService)
	{
		this.request = request;
		this.customerService = customerService;
	}

	@Override
	public boolean validate(final Customer entity)
	{
		if (isEmptyData(entity))
		{
			return false;
		}
		if (!checkForMatching(entity))
		{
			return false;
		}
		return isUniqueEmail(entity.getEmail());
	}

	private boolean isEmptyData(final Customer c)
	{
		if (c.getEmail() == null || c.getEmail().isEmpty())
		{
			request.setAttribute(ApplicationConstant.MESSAGE, ErrorConstant.EMPTY_EMAIL);
			return true;
		}
		if (c.getPassword() == null || c.getPassword().isEmpty())
		{
			request.setAttribute(ApplicationConstant.MESSAGE, ErrorConstant.EMPTY_PASSWORD);
			return true;
		}
		if (c.getPhoneNumber() == null)
		{
			request.setAttribute(ApplicationConstant.MESSAGE, ErrorConstant.EMPTY_NUMBER);
			return true;
		}
		if (c.getFirstName() == null || c.getFirstName().isEmpty())
		{
			request.setAttribute(ApplicationConstant.MESSAGE, ErrorConstant.EMPTY_FIRST_NAME);
			return true;
		}
		if (c.getLastName() == null || c.getLastName().isEmpty())
		{
			request.setAttribute(ApplicationConstant.MESSAGE, ErrorConstant.EMPTY_LAST_NAME);
			return true;
		}
		return c.getBirthdayDate() == null;
	}

	private boolean checkForMatching(final Customer c)
	{
		if (!isMatches(ApplicationConstant.EMAIL_PATTERN, c.getEmail()))
		{
			request.setAttribute(ApplicationConstant.MESSAGE, ErrorConstant.EMAIL_NOT_MATCHES);
			return false;
		}
		if (!isMatches(ApplicationConstant.PASSWORD_PATTERN, c.getPassword()))
		{
			request.setAttribute(ApplicationConstant.MESSAGE, ErrorConstant.PASSWORD_NOT_MATCHES);
			return false;
		}
		if (!isMatches(ApplicationConstant.NAME_PATTERN, c.getFirstName()))
		{
			request.setAttribute(ApplicationConstant.MESSAGE, ErrorConstant.NAME_NOT_MATCHES);
			return false;
		}
		if (!isMatches(ApplicationConstant.NAME_PATTERN, c.getLastName()))
		{
			request.setAttribute(ApplicationConstant.MESSAGE, ErrorConstant.NAME_NOT_MATCHES);
			return false;
		}
		if (!isMatches(ApplicationConstant.NUMBER_PATTERN, c.getPhoneNumber()))
		{
			request.setAttribute(ApplicationConstant.MESSAGE, ErrorConstant.NUMBER_NOT_MATCHES);
			return false;
		}
		return true;
	}

	private boolean isMatches(final String pattern, final String data)
	{
		this.pattern = Pattern.compile(pattern);
		final Matcher matcher = this.pattern.matcher(data);
		return matcher.matches();
	}

	private boolean isUniqueEmail(final String email)
	{
		final Optional<Customer> customer = customerService.getCustomerByEmail(email);
		if (customer.isPresent())
		{
			request.setAttribute(ApplicationConstant.MESSAGE, ErrorConstant.NOT_UNIQUE_EMAIL);
			return false;
		}
		return true;
	}
}
