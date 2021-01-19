package ua.com.shop.aimprosoft.controller.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ua.com.shop.aimprosoft.database.dao.impl.CustomerDaoImpl;
import ua.com.shop.aimprosoft.database.impl.HikariDataSourceImpl;
import ua.com.shop.aimprosoft.database.service.CustomerService;
import ua.com.shop.aimprosoft.database.service.impl.CustomerServiceImpl;
import ua.com.shop.aimprosoft.util.Validator;


public class ApplicationContextListener implements ServletContextListener
{
	@Override
	public void contextInitialized(final ServletContextEvent sce)
	{
		CustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl(new HikariDataSourceImpl()));
		ServletContext context = sce.getServletContext();
		context.setAttribute("customerService", customerService);
		Validator validator = new Validator();
		context.setAttribute("valid", validator);
		System.out.println("context initialized");
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce)
	{
		System.out.println("context destroyed");
	}
}
