package ua.com.aimprosoft.shop.exceptions;

public class IncorrectOperationException extends Exception
{
	public IncorrectOperationException()
	{
	}

	public IncorrectOperationException(final String message)
	{
		super(message);
	}

	public IncorrectOperationException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
