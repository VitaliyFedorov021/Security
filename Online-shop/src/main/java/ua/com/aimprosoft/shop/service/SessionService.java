package ua.com.aimprosoft.shop.service;

import javax.servlet.http.HttpSession;

import ua.com.aimprosoft.shop.models.Customer;


public interface SessionService
{
	Customer getCurrentCustomer(HttpSession session);
}
