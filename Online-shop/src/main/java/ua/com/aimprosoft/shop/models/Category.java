package ua.com.aimprosoft.shop.models;

public class Category extends Entity
{
	private String code;
	private String name;

	public Category()
	{
	}

	public Category(final String code, final String name)
	{
		this.code = code;
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(final String code)
	{
		this.code = code;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}
}
