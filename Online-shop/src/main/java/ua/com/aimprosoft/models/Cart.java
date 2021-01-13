package ua.com.aimprosoft.models;

import java.time.LocalDate;
import java.util.List;


public class Cart
{
	private int code;
	private int totalPrice;
	private LocalDate placedDate;
	private List<CartEntry> cartEntries;

	public int getCode()
	{
		return code;
	}

	public void setCode(final int code)
	{
		this.code = code;
	}

	public int getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(final int totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public LocalDate getPlacedDate()
	{
		return placedDate;
	}

	public void setPlacedDate(final LocalDate placedDate)
	{
		this.placedDate = placedDate;
	}

	public List<CartEntry> getCartEntries()
	{
		return cartEntries;
	}

	public void setCartEntries(final List<CartEntry> cartEntries)
	{
		this.cartEntries = cartEntries;
	}
}
