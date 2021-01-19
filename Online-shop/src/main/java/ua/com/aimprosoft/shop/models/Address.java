package ua.com.shop.aimprosoft.models;

import java.util.Objects;


public class Address extends Entity
{
	private String street;
	private String postalCode;
	private String town;
	private String region;
	private String country;

	public Address()
	{
	}

	public Address(final String street, final String postalCode, final String town, final String region,
			final String country)
	{
		this.street = street;
		this.postalCode = postalCode;
		this.town = town;
		this.region = region;
		this.country = country;
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
		final Address address = (Address) o;
		return Objects.equals(street, address.street) &&
				Objects.equals(postalCode, address.postalCode) &&
				Objects.equals(town, address.town) &&
				Objects.equals(region, address.region) &&
				Objects.equals(country, address.country);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(street, postalCode, town, region, country);
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
