package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.service.impl.CustomerServiceImpl;
import ua.com.aimprosoft.shop.util.Extractor;
import ua.com.aimprosoft.shop.util.Validator;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.impl.CustomerValidator;


public class SignUpCommand extends AbstractCommand
{
	private final CustomerService customerService;
	private final Validator<Customer> validator;

	public SignUpCommand()
	{
		this.customerService = new CustomerServiceImpl();
		validator = new CustomerValidator(customerService);
	}

	@Override
	public void process() throws ServletException, IOException
	{
		final List<Exception> exceptions = new ArrayList<>();
		final Customer customer = Extractor.mapCustomer(request);
		if (!validator.validate(customer, exceptions))
		{
			request.setAttribute(ApplicationConstant.MESSAGE, exceptions.get(0).getLocalizedMessage());
			forward(ApplicationConstant.SIGN_UP_PAGE);
			return;
		}
		customerService.registerCustomer(customer);
		forward(ApplicationConstant.LOGIN_PATH);
	}
}
