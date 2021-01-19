package ua.com.shop.aimprosoft.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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

	public void init() {
	}

	public abstract void process() throws ServletException, IOException;

	protected void forward(String page) throws ServletException, IOException
	{
		RequestDispatcher rDispatcher = servletContext.getRequestDispatcher("/" + page + ".jsp");
		rDispatcher.forward(request, response);
	}
}
