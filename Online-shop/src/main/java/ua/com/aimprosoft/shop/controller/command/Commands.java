package ua.com.aimprosoft.shop.controller.command;

import ua.com.aimprosoft.shop.controller.command.impl.HomePage;
import ua.com.aimprosoft.shop.controller.command.impl.LoginCommand;
import ua.com.aimprosoft.shop.controller.command.impl.LoginPage;
import ua.com.aimprosoft.shop.controller.command.impl.LogoutCommand;


public enum Commands
{
	HOME_PAGE(new HomePage()), LOGIN(new LoginCommand()), LOGIN_PAGE(new LoginPage()),
	LOGOUT(new LogoutCommand());
	private final AbstractCommand command;

	Commands(final AbstractCommand command)
	{
		this.command = command;
	}

	public AbstractCommand getCommand()
	{
		return command;
	}
}
