package ua.com.aimprosoft.models;

public class CartEntry
{
	private int quantity;
	private int entryNumber;
	private int totalPrice;
	private Product product;

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(final int quantity)
	{
		this.quantity = quantity;
	}

	public int getEntryNumber()
	{
		return entryNumber;
	}

	public void setEntryNumber(final int entryNumber)
	{
		this.entryNumber = entryNumber;
	}

	public int getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(final int totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public Product getProduct()
	{
		return product;
	}

	public void setProduct(final Product product)
	{
		this.product = product;
	}
}
