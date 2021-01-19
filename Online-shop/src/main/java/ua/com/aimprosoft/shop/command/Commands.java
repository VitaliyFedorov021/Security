package ua.com.shop.aimprosoft.command;

import ua.com.shop.aimprosoft.command.impl.HomePage;
import ua.com.shop.aimprosoft.command.impl.Login;
import ua.com.shop.aimprosoft.command.impl.LoginPage;
import ua.com.shop.aimprosoft.command.impl.Logout;


public enum Commands
{
	HOME_PAGE(new HomePage()), LOGIN(new Login()), LOGIN_PAGE(new LoginPage()),
	LOGOUT(new Logout());
	private AbstractCommand command;

	Commands(final AbstractCommand command)
	{
		this.command = command;
	}

	public AbstractCommand getCommand()
	{
		return command;
	}
}
