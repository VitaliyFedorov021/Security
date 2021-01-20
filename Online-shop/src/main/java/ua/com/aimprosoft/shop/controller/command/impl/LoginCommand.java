package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.util.Cryptor;
import ua.com.aimprosoft.shop.util.CustomerValidator;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class LoginCommand extends AbstractCommand
{
	private CustomerService customerService;
	private CustomerValidator customerValidator;

	@Override
	public void init() {
		this.customerService = (CustomerService) servletContext.getAttribute("customerService");
		this.customerValidator = (CustomerValidator) servletContext.getAttribute("valid");
	}

	@Override
	public void process() throws ServletException, IOException
	{
		init();
		final String email = request.getParameter(ApplicationConstant.EMAIL);
		final String password = request.getParameter(ApplicationConstant.PASSWORD);
		if (!isDataExists(email, password))
		{
			request.setAttribute(ApplicationConstant.MESSAGE, "empty data");
			forward(ApplicationConstant.LOGIN_PAGE_);
		}
		if (!customerValidator.validateEmail(email) || !customerValidator.validatePassword(password))
		{
			request.setAttribute(ApplicationConstant.MESSAGE, "login/password doesn't match to requirements");
			forward(ApplicationConstant.LOGIN_PAGE_);
			return;
		}
		final Customer customer = customerService.getCustomerByEmail(email);
		if (!isCustomerExists(customer, password))
		{
			request.setAttribute(ApplicationConstant.MESSAGE, "Invalid login/password");
			forward(ApplicationConstant.LOGIN_PAGE_);
			return;
		}
		final HttpSession session = request.getSession();
		session.setAttribute(ApplicationConstant.CUSTOMER, customer);
		response.sendRedirect(ApplicationConstant.SLASH);
	}

	private boolean isDataExists(final String email, final String password)
	{
		if (email == null) {
			return false;
		}
		return password != null;
	}

	private boolean isCustomerExists(final Customer customer, final String password)
	{
		String encryptedPassword = null;
		try
		{
			encryptedPassword = Cryptor.cryptPassword(password);
		}
		catch (final NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return customer != null && customer.getPassword().equals(encryptedPassword);
	}
}
