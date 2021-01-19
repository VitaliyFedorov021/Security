package ua.com.shop.aimprosoft.models;

import java.util.Date;
import java.util.Objects;


public class Customer extends Entity
{
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Gender gender;
	private Date birthdayDate;
	private String phoneNumber;

	public Customer()
	{
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		final Customer customer = (Customer) o;
		return Objects.equals(email, customer.email) &&
				Objects.equals(password, customer.password) &&
				Objects.equals(firstName, customer.firstName) &&
				Objects.equals(lastName, customer.lastName) &&
				gender == customer.gender &&
				Objects.equals(birthdayDate, customer.birthdayDate) &&
				Objects.equals(phoneNumber, customer.phoneNumber);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(email, password, firstName, lastName, gender, birthdayDate, phoneNumber);
	}

	public Customer(final String email, final String password, final String firstName, final String lastName,
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
