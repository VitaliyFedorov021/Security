package ua.com.aimprosoft.shop.dto;

public class AddressDto
{
	private String street;
	private String postalCode;
	private String town;
	private String region;
	private String country;

	public AddressDto()
	{
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(final String street)
	{
		this.street = street;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(final String postalCode)
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
}
