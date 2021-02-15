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
		if (!customer.isPresent())
		{
			sendWithErrorMessage(ErrorConstant.INCORRECT_LOGIN);
			return;
		}
		if (!isPasswordCorrect(customer.get(), password))
		{
			sendWithErrorMessage(ErrorConstant.INCORRECT_PASSWORD);
			return;
		}
		HttpSession session = request.getSession(false);
		session.setAttribute(ApplicationConstant.CUSTOMER, customer.get());
		String path = (String) session.getAttribute(ApplicationConstant.PATH);
		if (path == null)
		{
			path = ApplicationConstant.HOME;
		}
		response.sendRedirect(path);
	}

	private void sendWithErrorMessage(final String info) throws ServletException, IOException
	{
		request.setAttribute(ApplicationConstant.MESSAGE, info);
		forward(ApplicationConstant.LOGIN_PAGE);
		return;
	}

	private boolean isPasswordCorrect(final Customer customer, final String password)
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

		return customer.getPassword().equals(encryptedPassword);
	}
}
