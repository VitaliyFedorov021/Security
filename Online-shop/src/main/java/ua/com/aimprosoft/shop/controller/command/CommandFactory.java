package ua.com.aimprosoft.shop.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public final class CommandFactory
{
	public static AbstractCommand getCommand(final HttpServletRequest req)
	{
		final String command = req.getParameter(ApplicationConstant.COMMAND);
//		if (command == null) {
//			return
//		}
		return Commands.valueOf(command).getCommand();
	}
}
