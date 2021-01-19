package ua.com.shop.aimprosoft.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import ua.com.shop.aimprosoft.command.AbstractCommand;


public class Logout extends AbstractCommand
{
	@Override
	public void process() throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("customer");
		}
		forward("HomePage");
	}
}
