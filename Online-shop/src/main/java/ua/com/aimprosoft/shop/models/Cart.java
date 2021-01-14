package ua.com.aimprosoft.shop.models;

import java.util.Date;
import java.util.List;


public class Cart extends Entity
{
	private String code;
	private double totalPrice;
	private Date placedDate;
	private List<CartEntry> cartEntries;
	private Customer customer;
	private Address deliveryAddress;

	public Address getDeliveryAddress()
	{
		return deliveryAddress;
	}

	public void setDeliveryAddress(final Address deliveryAddress)
	{
		this.deliveryAddress = deliveryAddress;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(final Customer customer)
	{
		this.customer = customer;
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

	public Date getPlacedDate()
	{
		return placedDate;
	}

	public void setPlacedDate(final Date placedDate)
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
