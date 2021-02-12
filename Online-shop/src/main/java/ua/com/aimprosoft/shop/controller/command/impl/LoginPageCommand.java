package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class LoginPageCommand extends AbstractCommand
{
	@Override
	public void process() throws ServletException, IOException
	{
		logging(request, response);
		forward(ApplicationConstant.LOGIN_PAGE);
	}
}
