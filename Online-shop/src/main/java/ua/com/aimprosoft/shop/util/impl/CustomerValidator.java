package ua.com.aimprosoft.shop.util.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.util.Validator;


public class CustomerValidator implements Validator<Customer>
{
	private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\d])[a-zA-Z\\d]{6,}";
	private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

	public boolean validatePassword(final String password) {
		final Pattern pattern = Pattern.compile(PASSWORD_REGEX);
		final Matcher matcher = pattern.matcher(password);
		return !password.isEmpty() && (matcher.matches());
	}

	public boolean validateEmail(final String email) {
		final Pattern pattern = Pattern.compile(EMAIL_REGEX);
		final Matcher matcher = pattern.matcher(email);

		return !email.isEmpty() && (matcher.matches());
	}

	@Override
	public boolean isValid(final Customer entity)
	{
		if (!validatePassword(entity.getPassword())) {
			return false;
		}
		return validateEmail(entity.getEmail());
	}
}
