package ua.com.shop.aimprosoft.models;

import java.util.Objects;


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
		final Category category = (Category) o;
		return Objects.equals(code, category.code) &&
				Objects.equals(name, category.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(code, name);
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
