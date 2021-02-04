package ua.com.aimprosoft.shop.service.impl;

import java.util.List;
import java.util.Optional;

import ua.com.aimprosoft.shop.dao.CartEntryDao;
import ua.com.aimprosoft.shop.dao.impl.CartEntryDaoImpl;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.ProductService;


public class CartEntryServiceImpl implements CartEntryService
{
	private final CartEntryDao cartEntryDao;
	private final ProductService productService;

	public CartEntryServiceImpl()
	{
		this.cartEntryDao = new CartEntryDaoImpl();
		this.productService = new ProductServiceImpl();
	}

	@Override
	public void addEntry(final String code, final Cart cart, final int quantity)
	{
		final Optional<CartEntry> entryOptional = cartEntryDao.findByProductCode(code, cart.getCode());
		CartEntry cartEntry = null;
		Product product = null;
		if (!entryOptional.isPresent())
		{
			product = productService.findByCode(code);
			cartEntry = createEntry(product, cart, quantity);
			cartEntryDao.insertEntry(cartEntry);
			return;
		}
		cartEntry = entryOptional.get();
		updateEntry(cart, quantity, cartEntry);
	}

	private void updateEntry(final Cart cart, final int quantity, final CartEntry cartEntry)
	{
		final int newQuantity = cartEntry.getQuantity() + quantity;
		cartCalculation(cart, cartEntry, newQuantity);
		cartEntryDao.updateEntry(cartEntry);
	}

	private CartEntry createEntry(final Product product, final Cart cart, final int quantity)
	{
		final int number = cartEntryDao.findCurrentEntryNumber(cart.getCode());
		final CartEntry cartEntry = new CartEntry();
		cartEntry.setProduct(product);
		cartEntry.setEntryNumber(number + 1);
		cartEntry.setCart(cart);
		cartCalculation(cart, cartEntry, quantity);
		return cartEntry;
	}

	@Override
	public List<CartEntry> getEntries(final String code)
	{
		return cartEntryDao.findEntriesByCartCode(code);
	}

	private void cartCalculation(final Cart cart, final CartEntry cartEntry, final int quantity)
	{
		final double currentEntryPrice = cartEntry.getTotalPrice();
		cart.setTotalPrice(cart.getTotalPrice() - currentEntryPrice);
		cartEntry.setQuantity(quantity);
		final Product product = cartEntry.getProduct();
		final double entryPrice = product.getPrice() * quantity;
		cartEntry.setTotalPrice(entryPrice);
		cart.setTotalPrice(cart.getTotalPrice() + entryPrice);
	}
}
