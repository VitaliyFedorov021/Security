package ua.com.aimprosoft.shop.service.impl;

import java.util.List;
import java.util.Optional;

import ua.com.aimprosoft.shop.dao.CartEntryDao;
import ua.com.aimprosoft.shop.dao.impl.CartEntryDaoImpl;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.service.CalculationService;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.ProductService;


public class CartEntryServiceImpl implements CartEntryService
{
	private final CartEntryDao cartEntryDao;
	private final ProductService productService;
	private final CalculationService calculationService;

	public CartEntryServiceImpl()
	{
		this.cartEntryDao = new CartEntryDaoImpl();
		this.productService = new ProductServiceImpl();
		this.calculationService = new CalculationServiceImpl();
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
			calculationService.calculation(cart, cartEntry);
			cartEntryDao.insertEntry(cartEntry);
			return;
		}
		cartEntry = entryOptional.get();
		final int newQuantity = cartEntry.getQuantity() + quantity;
		updateEntry(cart, newQuantity, cartEntry);
	}

	private void updateEntry(final Cart cart, final int quantity, final CartEntry cartEntry)
	{
		cartEntry.setQuantity(quantity);
		calculationService.calculation(cart, cartEntry);
		cartEntryDao.updateEntry(cartEntry);
	}

	private CartEntry createEntry(final Product product, final Cart cart, final int quantity)
	{
		final int number = cartEntryDao.findCurrentEntryNumber(cart.getCode());
		final CartEntry cartEntry = new CartEntry();
		cartEntry.setProduct(product);
		cartEntry.setEntryNumber(number + 1);
		cartEntry.setQuantity(quantity);
		cartEntry.setCart(cart);
		return cartEntry;
	}

	@Override
	public List<CartEntry> getEntries(final String code)
	{
		return cartEntryDao.findEntriesByCartCode(code);
	}

	@Override
	public void deleteEntry(final String productCode, final Cart cart) throws IncorrectOperationException
	{
		final Optional<CartEntry> cartEntryOptional = cartEntryDao.findByProductCode(productCode, cart.getCode());
		if (!cartEntryOptional.isPresent())
		{
			throw new IncorrectOperationException();
		}
		final CartEntry cartEntry = cartEntryOptional.get();
		cartEntry.setQuantity(0);
		cartEntryDao.deleteEntry(cartEntry.getId());
		calculationService.calculation(cart, cartEntry);
	}

	@Override
	public void updateEntryQuantity(final String code, final int quantity, final Cart cart)
			throws IncorrectOperationException
	{
		final Optional<CartEntry> cartEntryOptional = cartEntryDao.findByProductCode(code, cart.getCode());
		if (!cartEntryOptional.isPresent())
		{
			throw new IncorrectOperationException();
		}
		final CartEntry cartEntry = cartEntryOptional.get();
		updateEntry(cart, quantity, cartEntry);
	}
}
