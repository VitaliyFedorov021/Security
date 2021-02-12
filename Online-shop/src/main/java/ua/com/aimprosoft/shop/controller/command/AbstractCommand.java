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

	protected void logging(HttpServletRequest request, HttpServletResponse response)
	{
		Enumeration<String> requestHeaderNames = request.getHeaderNames();
		System.out.println(ApplicationConstant.REQUEST_HEADERS);
		System.out.println(ApplicationConstant.DELIMITER);
		while (requestHeaderNames.hasMoreElements())
		{
			String name = requestHeaderNames.nextElement();
			System.out.println(name + ApplicationConstant.COLON + request.getHeader(name));
		}
		System.out.println(ApplicationConstant.REQUEST_PARAMS);
		System.out.println(ApplicationConstant.DELIMITER);
		Map<String, String[]> parameterMap = request.getParameterMap();
		for (Map.Entry<String, String[]> entry :
				parameterMap.entrySet())
		{
			String key = entry.getKey();
			if (key.equals(ApplicationConstant.PASSWORD)) {
				System.out.println(key + ApplicationConstant.COLON + encodePassword(Arrays.toString(entry.getValue())));
				continue;
			}
			System.out.println(key + ApplicationConstant.COLON + Arrays.toString(entry.getValue()));
		}
		System.out.println(ApplicationConstant.RESPONSE_HEADERS);
		System.out.println(ApplicationConstant.DELIMITER);
		Collection<String> responseHeaderNames = response.getHeaderNames();
		while (requestHeaderNames.hasMoreElements())
		{
			String name = requestHeaderNames.nextElement();
			System.out.println(name + ApplicationConstant.COLON + response.getHeader(name));
		}
		System.out.println(ApplicationConstant.END);
	}

	private String encodePassword(String password) {
		char[] passwordCharacters = password.toCharArray();
		Arrays.fill(passwordCharacters, '*');
		return Arrays.toString(passwordCharacters);
	}
}
