package ua.com.aimprosoft.shop.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


@WebFilter("/*")
public class LoggingFilter implements Filter
{
	@Override
	public void init(final FilterConfig filterConfig) throws ServletException
	{

	}

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
			final FilterChain filterChain)
			throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		logging(request, response);
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy()
	{

	}

	private void logging(HttpServletRequest request, HttpServletResponse response)
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
