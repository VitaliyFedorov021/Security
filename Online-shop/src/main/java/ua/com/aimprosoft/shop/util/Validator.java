package ua.com.aimprosoft.shop.util;

import java.util.List;


public interface Validator<T>
{
	boolean validate(T entity, List<Exception> exceptions);
}
