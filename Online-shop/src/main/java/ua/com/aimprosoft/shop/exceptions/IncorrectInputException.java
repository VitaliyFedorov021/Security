package ua.com.aimprosoft.shop.exceptions;

public class IncorrectInputException extends Exception
{
	public IncorrectInputException()
	{
	}

	public IncorrectInputException(final String message)
	{
		super(message);
	}
}
