package ua.com.shop.aimprosoft.command;

import static ua.com.shop.aimprosoft.command.Commands.HOME_PAGE;
import static ua.com.shop.aimprosoft.command.Commands.LOGIN;
import static ua.com.shop.aimprosoft.command.Commands.LOGIN_PAGE;
import static ua.com.shop.aimprosoft.command.Commands.LOGOUT;

import javax.servlet.http.HttpServletRequest;


public final class CommandFactory
{
	public static AbstractCommand getCommand(HttpServletRequest req)
	{
		String c = req.getParameter("command");
		switch (Commands.valueOf(c))
		{
			case HOME_PAGE:
			{
				return HOME_PAGE.getCommand();
			}
			case LOGIN:
			{
				return LOGIN.getCommand();
			}
			case LOGIN_PAGE:
			{
				return LOGIN_PAGE.getCommand();
			}
			case LOGOUT: {
				return LOGOUT.getCommand();
			}
			default: {
				return null;
			}
		}
	}
}
