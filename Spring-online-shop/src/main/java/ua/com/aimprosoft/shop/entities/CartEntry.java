package ua.com.aimprosoft.shop.entities;

public class CartEntry extends Entity
{
	private int quantity;
	private int entryNumber;
	private double totalPrice;
	private Product product;
	private Cart cart;

	public CartEntry()
	{
	}

	public Cart getCart()
	{
		return cart;
	}

	public void setCart(final Cart cart)
	{
		this.cart = cart;
	}

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

	public double getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(final double totalPrice)
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
