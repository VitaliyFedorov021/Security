package ua.com.aimprosoft.shop.models;

public class Product
{
	private int id;
	private String code;
	private String name;
	private double price;
	private String description;
	private Category category;

	public int getId()
	{
		return id;
	}

	public void setId(final int id)
	{
		this.id = id;
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
