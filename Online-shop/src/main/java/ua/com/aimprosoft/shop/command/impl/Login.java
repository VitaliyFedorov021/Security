package ua.com.shop.aimprosoft.command.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import ua.com.shop.aimprosoft.command.AbstractCommand;
import ua.com.shop.aimprosoft.database.service.CustomerService;
import ua.com.shop.aimprosoft.exceptions.IncorrectInputException;
import ua.com.shop.aimprosoft.exceptions.NoSuchCustomerException;
import ua.com.shop.aimprosoft.models.Customer;
import ua.com.shop.aimprosoft.util.Cryptor;
import ua.com.shop.aimprosoft.util.Validator;


public class Login extends AbstractCommand
{
	private CustomerService customerService;
	private Validator validator;

	public Login()
	{
	}

	@Override
	public void init()
	{
		this.customerService = (CustomerService) servletContext.getAttribute("customerService");
		this.validator = (Validator) servletContext.getAttribute("valid");
	}

	@Override
	public void process() throws ServletException, IOException
	{
		init();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (!validator.validateEmail(email) || !validator.validatePassword(password)) {
			request.setAttribute("msg", "login/password doesn't match to requirements");
			forward("LoginPage");
		}
		Customer customer = customerService.selectByLogin(email);
		if (isDataEmpty()) {
			forward("Error");
		}
		if (isCustomerExists(customer, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
		} else {
			request.setAttribute("msg", "Invalid login/password");
			forward("LoginPage");
		}
		forward("HomePage");
	}

	private boolean isDataEmpty()
	{
		if (request.getParameter("email") == null || request.getParameter("password") == null)
		{
			return true;
		}
		return false;
	}

	private boolean isCustomerExists(Customer customer, String password) {
		String cryptedPassword = null;
		try
		{
			cryptedPassword = Cryptor.cryptPassword(password);
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		if (customer != null && customer.getPassword().equals(cryptedPassword)) {
			return  true;
		}
		return false;
	}
}
