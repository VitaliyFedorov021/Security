package ua.com.aimprosoft.shop.dto;

import ua.com.aimprosoft.shop.entities.Category;

public class Product
{
	private String code;
	private String name;
	private String description;
	private double price;
	private Category category;

	public Product()
	{
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(final double price)
	{
		this.price = price;
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}
}
