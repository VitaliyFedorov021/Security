package ua.com.aimprosoft.shop.controller.command;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public abstract class AbstractCommand
{
	protected ServletContext servletContext;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public void init(final ServletContext servletContext, final HttpServletRequest request,
			final HttpServletResponse response)
	{
		this.servletContext = servletContext;
		this.request = request;
		this.response = response;
	}

	public abstract void process() throws ServletException, IOException;

	protected void forward(final String page) throws ServletException, IOException
	{
		final RequestDispatcher rDispatcher = servletContext.getRequestDispatcher(page);
		rDispatcher.forward(request, response);
	}
}
