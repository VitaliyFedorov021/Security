package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.service.impl.CustomerServiceImpl;
import ua.com.aimprosoft.shop.util.Cryptor;
import ua.com.aimprosoft.shop.util.Validator;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.impl.CustomerValidator;


public class LoginCommand extends AbstractCommand
{
	private final CustomerService customerService = new CustomerServiceImpl();
	private final Validator<Customer> customerValidator = new CustomerValidator();

	@Override
	public void process() throws ServletException, IOException
	{
		final String email = request.getParameter(ApplicationConstant.EMAIL);
		final String password = request.getParameter(ApplicationConstant.PASSWORD);
		final Customer customerFromDB = customerService.getCustomerByEmail(email);
		final Customer tempCustomer = new Customer(email, password);

		if (isCustomerExists(customerFromDB, tempCustomer)) {
			final HttpSession session = request.getSession();
			session.setAttribute(ApplicationConstant.CUSTOMER, customerFromDB);
			response.sendRedirect(ApplicationConstant.HOME);
		}
	}

	private boolean isCustomerExists(final Customer currentCustomer, final Customer tempCustomer)
			throws ServletException, IOException
	{
		if (!isDataExists(tempCustomer))
		{
			request.setAttribute(ApplicationConstant.MESSAGE, "empty data");
			forward(ApplicationConstant.LOGIN_PAGE);
			return false;
		}

		if (!customerValidator.isValid(tempCustomer))
		{
			request.setAttribute(ApplicationConstant.MESSAGE, "login/password doesn't match to requirements");
			forward(ApplicationConstant.LOGIN_PAGE);
			return false;
		}

		if (!isPasswordCorrect(currentCustomer, tempCustomer))
		{
			request.setAttribute(ApplicationConstant.MESSAGE, "Invalid login/password");
			forward(ApplicationConstant.LOGIN_PAGE);
			return false;
		}
		return true;

	}

	private boolean isDataExists(final Customer tempCustomer)
	{
		if (tempCustomer.getEmail() == null) {
			return false;
		}
		return tempCustomer.getPassword() != null;
	}

	private boolean isPasswordCorrect(final Customer customer, final Customer tempCustomer)
	{
		String encryptedPassword = null;
		try
		{
			encryptedPassword = Cryptor.cryptPassword(tempCustomer.getPassword());
		}
		catch (final NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return customer != null && customer.getPassword().equals(encryptedPassword);
	}
}
