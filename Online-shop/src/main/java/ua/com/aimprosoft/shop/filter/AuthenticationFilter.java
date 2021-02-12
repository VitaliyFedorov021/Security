package ua.com.aimprosoft.shop.filter;

import java.io.IOException;

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

import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;

@WebFilter("/login/page")
public class AuthenticationFilter implements Filter
{
	private ServletContext servletContext;
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
		String path = path = request.getHeader(ApplicationConstant.REFERER);
		if (path == null) {
			path = (String) servletContext.getAttribute(ApplicationConstant.PATH);
			servletContext.removeAttribute(ApplicationConstant.PATH);
		}
		servletContext.setAttribute(ApplicationConstant.PATH, path);
		filterChain.doFilter(request, response);
	}

}
