package ua.com.aimprosoft.shop.service.impl;

import java.util.Optional;

import ua.com.aimprosoft.shop.dao.CartEntryDao;
import ua.com.aimprosoft.shop.dao.impl.CartEntryDaoImpl;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.ProductService;


public class CartEntryServiceImpl implements CartEntryService
{
	private final CartEntryDao cartEntryDao;
	private final CartService cartService;
	private final ProductService productService;

	public CartEntryServiceImpl()
	{
		this.cartEntryDao = new CartEntryDaoImpl();
		this.cartService = new CartServiceImpl();
		this.productService = new ProductServiceImpl();
	}

	@Override
	public void addEntryToCart(final Customer customer, final int quantity, final String code)
	{
		final Optional<Cart> optionalCart = cartService.getCart(customer);
		final Cart cart = optionalCart.orElseGet(() -> cartService.saveCart(customer));
		addEntry(code, cart, quantity);
	}

	private void addEntry(final String code, final Cart cart, final int quantity)
	{
		final Optional<CartEntry> entryOptional = cartEntryDao.findByProductCode(code);
		CartEntry cartEntry = null;
		final Product product = productService.findByCode(code);
		if (!entryOptional.isPresent())
		{
			cartEntry = makeEntry(product, cart, quantity);
			cartService.updateCart(cart);
			cartEntryDao.insertEntry(cartEntry);
			return;
		}
		cartEntry = entryOptional.get();
		final int newQuantity = cartEntry.getQuantity() + quantity;
		cartEntry.setQuantity(newQuantity);
		cartEntry.setTotalPrice(product.getPrice() * newQuantity);
		cart.setTotalPrice(cart.getTotalPrice() + product.getPrice() * quantity);
		cartEntryDao.updateEntry(cartEntry);
		cartService.updateCart(cart);
	}

	private CartEntry makeEntry(final Product product, final Cart cart, final int quantity)
	{
		final int number = cartEntryDao.findCurrentEntryNumber(cart.getCode());
		final CartEntry cartEntry = new CartEntry();
		cartEntry.setProduct(product);
		cartEntry.setTotalPrice(product.getPrice() * quantity);
		cartEntry.setQuantity(quantity);
		cartEntry.setEntryNumber(number + 1);
		cart.setTotalPrice(cart.getTotalPrice() + cartEntry.getTotalPrice());
		cartEntry.setCart(cart);
		return cartEntry;
	}
}
