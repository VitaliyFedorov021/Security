package ua.com.aimprosoft.shop.dto;

public class Customer
{
	private String firstName;
	private String lastName;
	private String phoneNumber;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
}
