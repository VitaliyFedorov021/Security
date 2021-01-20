package ua.com.aimprosoft.shop.util;

import ua.com.aimprosoft.shop.models.Entity;


public interface Validator<T extends Entity>
{
	boolean isValid(T entity);
}
