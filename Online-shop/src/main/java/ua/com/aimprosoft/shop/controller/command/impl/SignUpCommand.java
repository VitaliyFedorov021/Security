package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.models.Gender;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.service.impl.CustomerServiceImpl;
import ua.com.aimprosoft.shop.util.Validator;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.constant.ErrorConstant;
import ua.com.aimprosoft.shop.util.impl.CustomerValidator;


public class SignUpCommand extends AbstractCommand
{
	private final CustomerService customerService;
	private final DateFormat dateFormat;

	public SignUpCommand()
	{
		this.customerService = new CustomerServiceImpl();
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	@Override
	public void process() throws ServletException, IOException
	{
		final Validator<Customer> validator = new CustomerValidator(request, customerService);
		final Customer customer = mapToModel(request);
		if (!validator.validate(customer))
		{
			forward(ApplicationConstant.SIGN_UP_PAGE);
			return;
		}
		customerService.addCustomer(customer);
		forward(ApplicationConstant.LOGIN_PATH);
	}

	private Customer mapToModel(final HttpServletRequest request) throws ServletException, IOException
	{
		final Customer customer = new Customer();
		customer.setEmail(request.getParameter(ApplicationConstant.EMAIL));
		customer.setPassword(request.getParameter(ApplicationConstant.PASSWORD));
		final Gender gender = Gender.valueOf(request.getParameter(ApplicationConstant.GENDER).toUpperCase());
		customer.setGender(gender);
		customer.setFirstName(request.getParameter(ApplicationConstant.FIRST_NAME));
		customer.setLastName(request.getParameter(ApplicationConstant.LAST_NAME));
		customer.setPhoneNumber(request.getParameter(ApplicationConstant.NUMBER));
		Date bDate = null;
		try
		{
			bDate = dateFormat.parse(request.getParameter(ApplicationConstant.BIRTHDAY));
		}
		catch (final ParseException e)
		{
			request.setAttribute(ApplicationConstant.MESSAGE, ErrorConstant.EMPTY_DATE);
			forward(ApplicationConstant.SIGN_UP_PAGE);
		}
		customer.setBirthdayDate(bDate);
		return customer;
	}
}
