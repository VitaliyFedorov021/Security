package ua.com.aimprosoft.shop.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.models.Gender;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class CustomerExtractor implements Extractor<Customer>
{
	@Override
	public Customer map(final HttpServletRequest request)
	{
		final DateFormat dateFormat = new SimpleDateFormat(ApplicationConstant.DATE_PATTERN);
		final Customer customer = new Customer();
		customer.setEmail(request.getParameter(ApplicationConstant.EMAIL));
		customer.setPassword(request.getParameter(ApplicationConstant.PASSWORD));
		final Gender gender = Gender.valueOf(request.getParameter(ApplicationConstant.GENDER).toUpperCase());
		customer.setGender(gender);
		customer.setFirstName(request.getParameter(ApplicationConstant.FIRST_NAME));
		customer.setLastName(request.getParameter(ApplicationConstant.LAST_NAME));
		customer.setPhoneNumber(request.getParameter(ApplicationConstant.NUMBER));
		final Date date = parseDate(dateFormat, request.getParameter(ApplicationConstant.BIRTHDAY));
		customer.setBirthdayDate(date);
		return customer;
	}

	private static Date parseDate(final DateFormat dateFormat, final String date)
	{
		Date bDate = null;
		try
		{
			bDate = dateFormat.parse(date);
		}
		catch (final ParseException e)
		{
			e.printStackTrace();
		}
		return bDate;
	}
}
