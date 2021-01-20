package ua.com.aimprosoft.shop.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class Cryptor
{
	public static String cryptPassword(final String password) throws NoSuchAlgorithmException
	{
		final MessageDigest digest = MessageDigest.getInstance(ApplicationConstant.ALGORITHM);
		digest.update(password.getBytes());
		final byte[] hash = digest.digest();
		final StringBuilder builder = new StringBuilder();
		for (final Byte b : hash)
		{
			builder.append(Integer.toHexString(b & 3));
		}
		return builder.toString();
	}
}
