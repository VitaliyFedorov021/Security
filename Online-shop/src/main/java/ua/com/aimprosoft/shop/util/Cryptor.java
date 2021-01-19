package ua.com.shop.aimprosoft.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Cryptor
{
	public static String cryptPassword(String password) throws NoSuchAlgorithmException
	{
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(password.getBytes());
		byte[] hash = digest.digest();
		StringBuilder builder = new StringBuilder();
		for (Byte b : hash)
		{
			builder.append(Integer.toHexString(b & 3));
		}
		return builder.toString();
	}
}
