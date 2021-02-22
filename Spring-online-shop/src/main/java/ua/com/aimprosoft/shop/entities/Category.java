package ua.com.aimprosoft.shop.entities;

public class Category extends Entity
{
	private String code;
	private String name;
	private int quantityOfProducts;

	public Category()
	{
	}

	public int getQuantityOfProducts()
	{
		return quantityOfProducts;
	}

	public void setQuantityOfProducts(final int quantityOfProducts)
	{
		this.quantityOfProducts = quantityOfProducts;
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
