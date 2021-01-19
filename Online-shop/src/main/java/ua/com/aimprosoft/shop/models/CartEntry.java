package ua.com.shop.aimprosoft.models;

import java.util.Objects;


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

	public CartEntry(final int quantity, final int entryNumber, final double totalPrice, final Product product,
			final Cart cart)
	{
		this.quantity = quantity;
		this.entryNumber = entryNumber;
		this.totalPrice = totalPrice;
		this.product = product;
		this.cart = cart;
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
		final CartEntry cartEntry = (CartEntry) o;
		return quantity == cartEntry.quantity &&
				entryNumber == cartEntry.entryNumber &&
				Double.compare(cartEntry.totalPrice, totalPrice) == 0 &&
				Objects.equals(product, cartEntry.product) &&
				Objects.equals(cart, cartEntry.cart);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(quantity, entryNumber, totalPrice, product, cart);
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
