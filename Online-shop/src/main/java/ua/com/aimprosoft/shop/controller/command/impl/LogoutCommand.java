package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class LogoutCommand extends AbstractCommand
{
	@Override
	public void process() throws ServletException, IOException
	{
		logging(request, response);
		final HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		response.sendRedirect(ApplicationConstant.HOME);
	}
}
