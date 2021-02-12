package ua.com.aimprosoft.shop.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener
{
	@Override
	public void sessionCreated(final HttpSessionEvent se)
	{
		HttpSession session = se.getSession();
	}

}
