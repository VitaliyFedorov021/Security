package ua.com.aimprosoft.shop.controller.command.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import ua.com.aimprosoft.shop.controller.command.AbstractCommand;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.ProductService;
import ua.com.aimprosoft.shop.service.impl.CartEntryServiceImpl;
import ua.com.aimprosoft.shop.service.impl.CartServiceImpl;
import ua.com.aimprosoft.shop.service.impl.ProductServiceImpl;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class AddToCartCommand extends AbstractCommand
{
	private final CartEntryService cartEntryService;
	private final CartService cartService;
	private final ProductService productService;

	public AddToCartCommand()
	{
		this.cartEntryService = new CartEntryServiceImpl();
		this.cartService = new CartServiceImpl();
		this.productService = new ProductServiceImpl();
	}

	@Override
	public void process() throws ServletException, IOException
	{
		final int quantity = Integer.parseInt(request.getParameter(ApplicationConstant.QUANTITY));
		final String code = request.getParameter(ApplicationConstant.PRODUCT_CODE);
		final Customer customer = (Customer) request.getSession().getAttribute(ApplicationConstant.CUSTOMER);
		final List<Cart> carts = cartService.getCartByCustomerId(customer.getId()).stream()
				.filter(c -> c.getDeliveryAddress() == null)
				.collect(Collectors.toList());
		Cart cart = null;
		if (carts.size() == 0)
		{
			cart = new Cart();
			cart.setCode(generateCode());
			cart.setPlacedDate(new Date());
			customer.setCart(cart);
			cart.setCustomer(customer);
			cartService.saveCart(cart);
		}
		else
		{
			cart = carts.get(0);
		}
		final Product product = productService.findByCode(code);
		final List<CartEntry> entries = cartEntryService.getEntriesByCartId(cart.getId()).stream()
				.filter(e -> e.getProduct().getCode().equals(code))
				.collect(Collectors.toList());
		if (entries.size() == 0)
		{
			final CartEntry cartEntry = makeEntry(product, cart, quantity);
			cartService.updateCart(cart);
			cartEntryService.addEntryToCart(cartEntry);
			cart.getCartEntries().add(cartEntry);
			return;
		}
		final CartEntry cartEntry = entries.get(entries.size() - 1);
		final int newQuantity = cartEntry.getQuantity() + quantity;
		cartEntry.setQuantity(newQuantity);
		cartEntry.setTotalPrice(product.getPrice() * newQuantity);
		cart.setTotalPrice(cart.getTotalPrice() + product.getPrice() * quantity);
		cartEntryService.updateEntry(cartEntry);
		cartService.updateCart(cart);
	}

	private CartEntry makeEntry(final Product product, final Cart cart, final int quantity)
	{
		final List<CartEntry> entries = cartEntryService.getEntriesByCartId(cart.getId());
		int number = 1;
		final CartEntry cartEntry = new CartEntry();
		cartEntry.setProduct(product);
		cartEntry.setTotalPrice(product.getPrice() * quantity);
		cartEntry.setQuantity(quantity);
		if (entries.size() > 0)
		{
			number = entries.get(entries.size() - 1).getEntryNumber() + 1;
		}
		cartEntry.setEntryNumber(number);
		cart.setTotalPrice(cart.getTotalPrice() + cartEntry.getTotalPrice());
		cartEntry.setCart(cart);
		return cartEntry;
	}

	private String generateCode()
	{
		final int leftLimit = 97;
		final int rightLimit = 122;
		final int targetStringLength = 10;
		final Random random = new Random();
		final StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++)
		{
			final int randomLimitedInt = leftLimit + (int)
					(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		return buffer.toString();
	}
}

