package ua.com.aimprosoft.shop.dto;

import ua.com.aimprosoft.shop.entities.Product;

public class CartEntry
{
	private int quantity;
	private double totalPrice;
	private Product product;

	public CartEntry()
	{
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(final int quantity)
	{
		this.quantity = quantity;
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
