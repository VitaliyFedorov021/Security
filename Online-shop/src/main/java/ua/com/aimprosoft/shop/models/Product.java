package ua.com.shop.aimprosoft.models;

import java.util.Objects;


public class Product extends Entity
{
	private String code;
	private String name;
	private double price;
	private String description;
	private Category category;

	public Product()
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
		final Product product = (Product) o;
		return Double.compare(product.price, price) == 0 &&
				Objects.equals(code, product.code) &&
				Objects.equals(name, product.name) &&
				Objects.equals(description, product.description) &&
				Objects.equals(category, product.category);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(code, name, price, description, category);
	}

	public Product(final String code, final String name, final double price, final String description,
			final Category category)
	{
		this.code = code;
		this.name = name;
		this.price = price;
		this.description = description;
		this.category = category;
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory(final Category category)
	{
		this.category = category;
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

	public double getPrice()
	{
		return price;
	}

	public void setPrice(final double price)
	{
		this.price = price;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}
}
