package ua.com.shop.aimprosoft.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.shop.aimprosoft.command.AbstractCommand;


@WebServlet(urlPatterns = "/servlet")
public class FrontController extends HttpServlet
{
//	private CustomerService cService;
	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException
	{
		AbstractCommand command = getCommand(req);
		command.init(getServletContext(), req, resp);
		command.process();
	}

	@Override
	public void init() throws ServletException
	{
//		cService = getServletContext().getAttribute("Customer");
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException
	{
		doGet(req, resp);
	}

	private AbstractCommand getCommand(HttpServletRequest request)
	{
		try
		{
			Class t = Class.forName(String.format("ua.com.shop.aimprosoft.command.impl.%sCommand",
					request.getParameter("command")));
			AbstractCommand abstractCommand = (AbstractCommand) t.asSubclass(AbstractCommand.class).newInstance();
			return abstractCommand;
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
