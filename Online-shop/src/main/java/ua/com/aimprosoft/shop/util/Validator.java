package ua.com.aimprosoft.shop.util;

public interface Validator<T>
{
	boolean validate(T entity);
}
