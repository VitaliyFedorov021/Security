package ua.com.aimprosoft.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.controller.command.CommandFactory;

@WebServlet("/")
public class FrontController extends HttpServlet
{
	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException
	{
		final AbstractCommand command = getCommand(req);
		command.init(getServletContext(), req, resp);
		command.process();
	}

	@Override
	public void init() throws ServletException
	{
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException
	{
		doGet(req, resp);
	}

	private AbstractCommand getCommand(final HttpServletRequest request)
	{
		return CommandFactory.getCommand(request);
	}
}
