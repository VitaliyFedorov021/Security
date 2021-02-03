package ua.com.aimprosoft.shop.service.impl;

import javax.servlet.http.HttpSession;

import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.SessionService;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class SessionServiceImpl implements SessionService
{
	@Override
	public Customer getCurrentCustomer(final HttpSession session)
	{
		return (Customer) session.getAttribute(ApplicationConstant.CUSTOMER);
	}
}
