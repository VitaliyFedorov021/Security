package ua.com.aimprosoft.models;

import java.time.LocalDate;


public class Customer
{
	private String email;

	public String getEmail()
	{
		return email;
	}

	private String password;
	private String firstName, lastName;
	private String gender;
	private LocalDate birthdayDate;
	private String phoneNumber;
	private Cart cart;

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public Cart getCart()
	{
		return cart;
	}

	public void setCart(final Cart cart)
	{
		this.cart = cart;
	}
	
	public String getPassword()
	{
		return password;
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}

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

	public String getGender()
	{
		return gender;
	}

	public void setGender(final String gender)
	{
		this.gender = gender;
	}

	public LocalDate getBirthdayDate()
	{
		return birthdayDate;
	}

	public void setBirthdayDate(final LocalDate birthdayDate)
	{
		this.birthdayDate = birthdayDate;
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
