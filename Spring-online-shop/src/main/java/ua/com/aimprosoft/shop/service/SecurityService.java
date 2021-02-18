package ua.com.aimprosoft.shop.service;

public interface SecurityService
{
	String getCurrentUsername();

	void autoLogin(String email, String password);
}
