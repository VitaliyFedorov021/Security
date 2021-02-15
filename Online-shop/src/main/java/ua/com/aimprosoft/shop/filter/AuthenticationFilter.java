package ua.com.aimprosoft.shop.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
	private static final List<String> loginRequiredPages;

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException
	{

	}

	static
	{
		loginRequiredPages = new ArrayList<>();
		loginRequiredPages.add(ApplicationConstant.SHOW_CART_COMMAND);
		loginRequiredPages.add(ApplicationConstant.CONFIRMATION_PATH);
		loginRequiredPages.add(ApplicationConstant.CHECKOUT_COMMAND);
	}

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
			final FilterChain filterChain)
			throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		String path = getPath(request);
		boolean isLoggedIn = (session.getAttribute(ApplicationConstant.CUSTOMER) != null);
		if (!isLoggedIn && isPageRequireLogin(path))
		{
			session.setAttribute(ApplicationConstant.PATH, path);
			response.sendRedirect(ApplicationConstant.LOGIN_PAGE_PATH);
			return;
		}
		filterChain.doFilter(request, response);
	}

	private boolean isPageRequireLogin(String path)
	{
		return loginRequiredPages.stream().anyMatch(path::equals);
	}

	private String getPath(HttpServletRequest request) {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		String queryString = request.getQueryString();
		if (queryString != null)
		{
			path += ApplicationConstant.QUESTION_MARK + request.getQueryString();
		}
		return path;
	}

}
