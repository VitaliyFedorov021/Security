package ua.com.aimprosoft.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.aimprosoft.shop.dao.CartDao;
import ua.com.aimprosoft.shop.dao.CartEntryDao;
import ua.com.aimprosoft.shop.dto.CartDto;
import ua.com.aimprosoft.shop.entities.Cart;
import ua.com.aimprosoft.shop.entities.CartEntry;
import ua.com.aimprosoft.shop.entities.Product;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;
import ua.com.aimprosoft.shop.service.CalculationService;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.ProductService;
import ua.com.aimprosoft.shop.util.converters.CartConverter;


@Component
public class CartEntryServiceImpl implements CartEntryService
{
	@Autowired
	private final CartDao cartDao;
	@Autowired
	private final CartEntryDao cartEntryDao;
	@Autowired
	private final ProductService productService;
	@Autowired
	private final CalculationService calculationService;

	public CartEntryServiceImpl(final CartDao cartDao, final CartEntryDao cartEntryDao,
			final ProductService productService, final CalculationService calculationService)
	{
		this.cartDao = cartDao;
		this.cartEntryDao = cartEntryDao;
		this.productService = productService;
		this.calculationService = calculationService;
	}

	@Override
	public void addEntry(final String code, final CartDto cartDto, final int quantity)
	{
		final Cart cart = CartConverter.dtoToEntity(cartDto);
		final Optional<CartEntry> entryOptional = cartEntryDao.findByProductCode(code, cart.getCode());
		CartEntry cartEntry = null;
		Product product = null;
		if (!entryOptional.isPresent())
		{
			product = productService.getByProductCode(code);
			cartEntry = createEntry(product, cart, quantity);
			cartEntryDao.insertEntry(cartEntry);
			calculationService.calculation(cart);
			return;
		}
		cartEntry = entryOptional.get();
		final int newQuantity = cartEntry.getQuantity() + quantity;
		updateEntry(cart, newQuantity, cartEntry);
	}

	@Override
	public List<CartEntry> getEntriesByCartCode(final String cartCode)
	{
		return cartEntryDao.findEntriesByCartCode(cartCode);
	}

	@Override
	public void updateEntryQuantity(final String code, final int quantity, final CartDto cartDto)
			throws IncorrectOperationException
	{
		final Cart cart = CartConverter.dtoToEntity(cartDto);
		final Optional<CartEntry> cartEntryOptional = cartEntryDao.findByProductCode(code, cart.getCode());
		if (!cartEntryOptional.isPresent())
		{
			throw new IncorrectOperationException();
		}
		final CartEntry cartEntry = cartEntryOptional.get();
		updateEntry(cart, quantity, cartEntry);
	}

	@Override
	public void deleteEntry(final CartDto cartDto, final String productCode) throws IncorrectOperationException
	{
		final Cart cart = CartConverter.dtoToEntity(cartDto);
		final Optional<CartEntry> cartEntryOptional = cartEntryDao.findByProductCode(productCode, cart.getCode());
		if (!cartEntryOptional.isPresent())
		{
			throw new IncorrectOperationException("No entry");
		}
		final CartEntry cartEntry = cartEntryOptional.get();
		cartEntry.setQuantity(0);
		cartEntryDao.updateEntry(cartEntry);
		calculationService.calculation(cart);
		cartEntryDao.deleteEntry(cartEntry.getId());
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

	private void updateEntry(final Cart cart, final int quantity, final CartEntry cartEntry)
	{
		cartEntry.setQuantity(quantity);
		cartEntryDao.updateEntry(cartEntry);
		calculationService.calculation(cart);
	}
}
