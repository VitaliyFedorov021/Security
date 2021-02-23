package ua.com.aimprosoft.shop.dto;

public class CategoryDto
{
	private String code;
	private String name;
	private int quantityOfProducts;

	public CategoryDto()
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

	public CategoryDto(final String code, final String name)
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
