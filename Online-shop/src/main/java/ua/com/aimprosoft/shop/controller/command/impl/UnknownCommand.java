package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.constant.ErrorConstant;


public class UnknownCommand extends AbstractCommand
{
	@Override
	public void process() throws ServletException, IOException
	{
		logging(request, response);
		request.setAttribute(ApplicationConstant.MESSAGE, ErrorConstant.UNKNOWN_COMMAND);
		forward(ApplicationConstant.ERROR_PATH);
	}
}
