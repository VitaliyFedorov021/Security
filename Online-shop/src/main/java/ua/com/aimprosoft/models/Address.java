package ua.com.aimprosoft.models;

public class Address
{
	private String street;
	private int postalCode;
	private String town;
	private String region;
	private String country;
	private Customer customer;

	public String getStreet()
	{
		return street;
	}

	public void setStreet(final String street)
	{
		this.street = street;
	}

	public int getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(final int postalCode)
	{
		this.postalCode = postalCode;
	}

	public String getTown()
	{
		return town;
	}

	public void setTown(final String town)
	{
		this.town = town;
	}

	public String getRegion()
	{
		return region;
	}

	public void setRegion(final String region)
	{
		this.region = region;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(final String country)
	{
		this.country = country;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(final Customer customer)
	{
		this.customer = customer;
	}
}
