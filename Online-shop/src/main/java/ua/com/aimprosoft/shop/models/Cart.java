package ua.com.shop.aimprosoft.models;

import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Cart extends Entity
{
	private String code;
	private double totalPrice;
	private Date placedDate;
	private List<CartEntry> cartEntries;
	private Customer customer;
	private Address deliveryAddress;

	public Cart()
	{
	}

	public Cart(final String code, final double totalPrice, final Date placedDate,
			final List<CartEntry> cartEntries, final Customer customer, final Address deliveryAddress)
	{
		this.code = code;
		this.totalPrice = totalPrice;
		this.placedDate = placedDate;
		this.cartEntries = cartEntries;
		this.customer = customer;
		this.deliveryAddress = deliveryAddress;
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
		final Cart cart = (Cart) o;
		return Double.compare(cart.totalPrice, totalPrice) == 0 &&
				Objects.equals(code, cart.code) &&
				Objects.equals(placedDate, cart.placedDate) &&
				Objects.equals(cartEntries, cart.cartEntries) &&
				Objects.equals(customer, cart.customer) &&
				Objects.equals(deliveryAddress, cart.deliveryAddress);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(code, totalPrice, placedDate, cartEntries, customer, deliveryAddress);
	}

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
