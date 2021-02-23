package ua.com.aimprosoft.shop.forms;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import ua.com.aimprosoft.shop.entities.Gender;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class CustomerForm
{
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Gender gender;
	@DateTimeFormat(pattern = ApplicationConstant.DATE_PATTERN)
	private Date birthdayDate;
	private String phoneNumber;

	public CustomerForm()
	{
	}

	public CustomerForm(final String email, final String password)
	{
		this.email = email;
		this.password = password;
	}

	public CustomerForm(final String email, final String password, final String firstName, final String lastName,
			final Gender gender, final Date birthdayDate, final String phoneNumber)
	{
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthdayDate = birthdayDate;
		this.phoneNumber = phoneNumber;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
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

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
}
