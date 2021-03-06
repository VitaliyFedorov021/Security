package ua.com.aimprosoft.shop.dto;

import java.util.Date;

import ua.com.aimprosoft.shop.entities.Gender;


public class CustomerDto
{
	private int id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Gender gender;
	private Date birthdayDate;
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

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}

	public Gender getGender()
	{
		return gender;
	}

	public void setGender(final Gender gender)
	{
		this.gender = gender;
	}

	public Date getBirthdayDate()
	{
		return birthdayDate;
	}

	public void setBirthdayDate(final Date birthdayDate)
	{
		this.birthdayDate = birthdayDate;
	}

	public int getId()
	{
		return id;
	}

	public void setId(final int id)
	{
		this.id = id;
	}

	public void setEmail(final String email)
	{
		this.email = email;
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
