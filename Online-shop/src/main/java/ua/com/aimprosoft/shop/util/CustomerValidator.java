package ua.com.aimprosoft.shop.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomerValidator
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

}
