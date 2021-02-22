package ua.com.aimprosoft.shop.dto;

import java.util.Date;
import java.util.List;

import ua.com.aimprosoft.shop.entities.Address;
import ua.com.aimprosoft.shop.entities.CartEntry;


public class Cart
{
	private String code;
	private double totalPrice;
	private List<CartEntry> cartEntries;
	private Date placedDate;
	private Address deliveryAddress;

	public Cart()
	{
	}

	public Date getPlacedDate()
	{
		return placedDate;
	}

	public void setPlacedDate(final Date placedDate)
	{
		this.placedDate = placedDate;
	}

	public Address getDeliveryAddress()
	{
		return deliveryAddress;
	}

	public void setDeliveryAddress(final Address deliveryAddress)
	{
		this.deliveryAddress = deliveryAddress;
	}


	public String getCode()
	{
		return code;
	}

	public void setCode(final String code)
	{
		this.code = code;
	}

	public double getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(final double totalPrice)
	{
		this.totalPrice = totalPrice;
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
