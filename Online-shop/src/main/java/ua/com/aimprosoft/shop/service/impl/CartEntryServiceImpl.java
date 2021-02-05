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
			cartCalculation(cart, cartEntry, 0);
			return;
		}
		cartEntry = entryOptional.get();
		final int newQuantity = cartEntry.getQuantity() + quantity;
		updateEntry(cart, newQuantity, cartEntry);
	}

	private void updateEntry(final Cart cart, final int quantity, final CartEntry cartEntry)
	{
		final double currentPrice = cartEntry.getTotalPrice();
		cartEntry.setQuantity(quantity);
		final double newPrice = cartEntry.getProduct().getPrice() * quantity;
		cartEntry.setTotalPrice(newPrice);
		cartEntryDao.updateEntry(cartEntry);
		cartCalculation(cart, cartEntry, currentPrice);
	}

	private CartEntry createEntry(final Product product, final Cart cart, final int quantity)
	{
		final int number = cartEntryDao.findCurrentEntryNumber(cart.getCode());
		final CartEntry cartEntry = new CartEntry();
		cartEntry.setProduct(product);
		cartEntry.setEntryNumber(number + 1);
		cartEntry.setQuantity(quantity);
		cartEntry.setTotalPrice(quantity * product.getPrice());
		cartEntry.setCart(cart);
		return cartEntry;
	}

	@Override
	public List<CartEntry> getEntries(final String code)
	{
		return cartEntryDao.findEntriesByCartCode(code);
	}

	@Override
	public void deleteEntry(final String productCode, final Cart cart)
	{
		final Optional<CartEntry> cartEntryOptional = cartEntryDao.findByProductCode(productCode, cart.getCode());
		final CartEntry cartEntry = cartEntryOptional.get();
		final double currentPrice = cartEntry.getTotalPrice();
		cartEntryDao.deleteEntry(cartEntry.getId());
		cartEntry.setTotalPrice(0);
		cartCalculation(cart, cartEntry, currentPrice);
	}

	@Override
	public void updateEntryQuantity(final String code, final int quantity, final Cart cart)
	{
		final Optional<CartEntry> cartEntryOptional = cartEntryDao.findByProductCode(code, cart.getCode());
		final CartEntry cartEntry = cartEntryOptional.get();
		updateEntry(cart, quantity, cartEntry);
	}

	private void cartCalculation(final Cart cart, final CartEntry cartEntry, final double currentEntryPrice)
	{
		final double newPrice = cartEntry.getTotalPrice();
		cart.setTotalPrice(cart.getTotalPrice() - currentEntryPrice + newPrice);
	}
}
