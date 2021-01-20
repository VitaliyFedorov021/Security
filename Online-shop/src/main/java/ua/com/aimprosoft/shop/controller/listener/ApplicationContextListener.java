package ua.com.aimprosoft.shop.controller.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ua.com.aimprosoft.shop.dao.impl.CustomerDaoImpl;
import ua.com.aimprosoft.shop.database.impl.HikariDataSourceImpl;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.service.impl.CustomerServiceImpl;
import ua.com.aimprosoft.shop.util.CustomerValidator;

@WebListener
public class ApplicationContextListener implements ServletContextListener
{
	@Override
	public void contextInitialized(final ServletContextEvent sce)
	{
		final CustomerService customerService = new CustomerServiceImpl(new CustomerDaoImpl(new HikariDataSourceImpl()));
		final ServletContext context = sce.getServletContext();
		context.setAttribute("customerService", customerService);
		final CustomerValidator customerValidator = new CustomerValidator();
		context.setAttribute("valid", customerValidator);
		System.out.println("context initialized");
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce)
	{
		System.out.println("context destroyed");
	}
}
