package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.service.impl.CustomerServiceImpl;
import ua.com.aimprosoft.shop.util.Cryptor;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.constant.ErrorConstant;

public class LoginCommand extends AbstractCommand
{
	private final CustomerService customerService;

	public LoginCommand()
	{
		this.customerService = new CustomerServiceImpl();
	}

	@Override
	public void process() throws ServletException, IOException
	{

		final String email = request.getParameter(ApplicationConstant.EMAIL);
		final String password = request.getParameter(ApplicationConstant.PASSWORD);
		if (email == null || email.isEmpty())
		{
			sendWithErrorMessage(ErrorConstant.EMPTY_EMAIL);
			return;
		}

		if (password == null || password.isEmpty())
		{
			sendWithErrorMessage(ErrorConstant.EMPTY_PASSWORD);
			return;
		}
		final Optional<Customer> customer = customerService.getCustomerByEmail(email);

		if (!isDataCorrect(customer, email, password))
		{
			sendWithErrorMessage(ErrorConstant.INCORRECT_LOGIN_OR_PASSWORD);
			return;
		}
		final HttpSession session = request.getSession();
		session.setAttribute(ApplicationConstant.CUSTOMER, customer.get());
		response.sendRedirect(ApplicationConstant.HOME);
	}

	private void sendWithErrorMessage(final String info) throws ServletException, IOException
	{
		request.setAttribute(ApplicationConstant.MESSAGE, info);
		forward(ApplicationConstant.LOGIN_PAGE);
		return;
	}

	private boolean isDataCorrect(final Optional<Customer> optionalCustomer, final String email, final String password)
	{
		if (!optionalCustomer.isPresent())
		{
			return false;
		}
		final Customer customer = optionalCustomer.get();
		String encryptedPassword = null;
		try
		{
			encryptedPassword = Cryptor.cryptPassword(password);
		}
		catch (final NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		if (!customer.getEmail().equals(email))
		{
			return false;
		}

		return customer.getPassword().equals(encryptedPassword);
	}
}
