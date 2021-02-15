package ua.com.aimprosoft.shop.util;

import java.util.List;


public interface Validator<T>
{
	void validate(T entity, final List<String> exceptions);
}
