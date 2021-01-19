package ua.com.shop.aimprosoft.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validator
{
	private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\d])[a-zA-Z\\d]{6,}";
	private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

	public boolean validatePassword(String password) {
		Pattern pattern = Pattern.compile(PASSWORD_REGEX);
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches() && !password.isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches() && !email.isEmpty()) {
			return true;
		}
		return false;
	}

}
