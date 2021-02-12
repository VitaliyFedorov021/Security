package ua.com.aimprosoft.shop.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


@WebFilter("/*")
public class AuthenticationFilter implements Filter
{
	private ServletContext servletContext;
	private static final List<String> loginRequiredPages;

	static
	{
		loginRequiredPages = new ArrayList<>();
		loginRequiredPages.add(ApplicationConstant.SHOW_CART_COMMAND);
		loginRequiredPages.add(ApplicationConstant.CONFIRMATION_PATH);
		loginRequiredPages.add(ApplicationConstant.CHECKOUT_COMMAND);
	}


	@Override
	public void init(final FilterConfig filterConfig) throws ServletException
	{
		servletContext = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
			final FilterChain filterChain)
			throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String path = request.getRequestURI().substring(request.getContextPath().length());
		String queryString = request.getQueryString();
		if (queryString != null)
		{
			path += ApplicationConstant.QUESTION_MARK + request.getQueryString();
		}
		if (path.equals(ApplicationConstant.SLASH))
		{
			servletContext.removeAttribute(ApplicationConstant.PATH);
		}
		HttpSession session = request.getSession(false);
		boolean isLoggedIn = (session != null && session.getAttribute(ApplicationConstant.CUSTOMER) != null);
		if (!isLoggedIn && isPageRequireLogin(path))
		{
			servletContext.setAttribute(ApplicationConstant.PATH, path);
			request.getRequestDispatcher(ApplicationConstant.LOGIN_PAGE_PATH).forward(request, response);
		}
		filterChain.doFilter(request, response);
	}

	private boolean isPageRequireLogin(String path)
	{
		for (String pages : loginRequiredPages)
		{
			if (path.equals(pages))
			{
				return true;
			}
		}
		return false;
	}
}
