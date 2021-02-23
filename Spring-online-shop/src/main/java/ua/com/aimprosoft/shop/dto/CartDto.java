package ua.com.aimprosoft.shop.dto;

import java.util.Date;
import java.util.List;

import ua.com.aimprosoft.shop.entities.Address;


public class CartDto
{
	private int id;
	private String code;
	private double totalPrice;
	private List<CartEntryDto> cartEntries;
	private Date placedDate;
	private Address deliveryAddress;

	public CartDto()
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

	public int getId()
	{
		return id;
	}

	public void setId(final int id)
	{
		this.id = id;
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

	public List<CartEntryDto> getCartEntries()
	{
		return cartEntries;
	}

	public void setCartEntries(final List<CartEntryDto> cartEntries)
	{
		this.cartEntries = cartEntries;
	}
}
