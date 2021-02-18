package ua.com.aimprosoft.shop.util;

import javax.servlet.http.HttpServletRequest;


public interface Extractor<T>
{
	T map(HttpServletRequest request);
}
