package ua.com.shop.aimprosoft.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;

import ua.com.shop.aimprosoft.command.AbstractCommand;


public class LoginPage extends AbstractCommand
{
	public void process() throws ServletException, IOException
	{
		forward("/LoginPage");
	}
}
