package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;


public class SignUpPage extends AbstractCommand
{
	@Override
	public void process() throws ServletException, IOException
	{
		forward("/signup");
	}
}
