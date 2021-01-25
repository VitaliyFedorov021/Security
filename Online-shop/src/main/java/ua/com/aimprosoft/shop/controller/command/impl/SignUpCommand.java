package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.service.impl.CustomerServiceImpl;
import ua.com.aimprosoft.shop.util.CustomerExtractor;
import ua.com.aimprosoft.shop.util.Extractor;
import ua.com.aimprosoft.shop.util.Validator;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.impl.CustomerValidator;


public class SignUpCommand extends AbstractCommand
{
	private final CustomerService customerService;
	private final Validator<Customer> validator;
	private final Extractor<Customer> extractor;

	public SignUpCommand()
	{
		this.customerService = new CustomerServiceImpl();
		validator = new CustomerValidator(customerService);
		extractor = new CustomerExtractor();
	}

	@Override
	public void process() throws ServletException, IOException
	{
		final List<Exception> exceptions = new ArrayList<>();
		final Customer customer = extractor.map(request);
		validator.validate(customer, exceptions);
		if (exceptions.size() > 0)
		{
			request.setAttribute(ApplicationConstant.MESSAGE, exceptions);
			forward(ApplicationConstant.SIGN_UP_PAGE);
			return;
		}
		customerService.registerCustomer(customer);
		forward(ApplicationConstant.LOGIN_PATH);
	}
}
