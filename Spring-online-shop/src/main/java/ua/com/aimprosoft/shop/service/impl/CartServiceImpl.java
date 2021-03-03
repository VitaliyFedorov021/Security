package ua.com.aimprosoft.shop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.aimprosoft.shop.dao.AddressDao;
import ua.com.aimprosoft.shop.dao.CartDao;
import ua.com.aimprosoft.shop.dto.AddressDto;
import ua.com.aimprosoft.shop.dto.CartDto;
import ua.com.aimprosoft.shop.dto.CustomerDto;
import ua.com.aimprosoft.shop.entities.Address;
import ua.com.aimprosoft.shop.entities.Cart;
import ua.com.aimprosoft.shop.entities.CartEntry;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.converters.AddressConverter;
import ua.com.aimprosoft.shop.util.converters.CartConverter;
import ua.com.aimprosoft.shop.util.converters.CustomerConverter;


@Service
public class CartServiceImpl implements CartService
{
	@Autowired
	private final CartDao cartDao;
	@Autowired
	private final CartEntryService cartEntryService;
	@Autowired
	private final AddressDao addressDao;

	public CartServiceImpl(final CartDao cartDao, final CartEntryService cartEntryService,
			final AddressDao addressDao)
	{
		this.cartDao = cartDao;
		this.cartEntryService = cartEntryService;
		this.addressDao = addressDao;
	}

	@Override
	public void addProductToCart(final CustomerDto customerDto, final String productCode, final int quantity)
	{
		final CartDto cartDto = getActiveCart(customerDto);
		cartEntryService.addEntry(productCode, cartDto, quantity);
	}

	@Override
	public void addProductToAnonymousCart(final HttpSession session, final String productCode, final int quantity)
	{
		CartDto cartDto = getActiveCart(session);
		cartEntryService.addEntry(productCode, cartDto, quantity);
	}

	@Override
	public CartDto getActiveCart(final CustomerDto customerDto)
	{
		final Optional<Cart> cartOptional = cartDao.findActiveCart(customerDto.getEmail());
		if (!cartOptional.isPresent())
		{
			final Cart cart = new Cart();
			cart.setCode(generateCode());
			cart.setCustomer(CustomerConverter.dtoToEntity(customerDto));
			cartDao.insertCart(cart);
			return CartConverter.entityToDto(cart);
		}
		final Cart cart = cartOptional.get();
		final List<CartEntry> entries = cartEntryService.getEntriesByCartCode(cart.getCode());
		cart.setCartEntries(entries);
		return CartConverter.entityToDto(cartOptional.get());
	}

	@Override
	public CartDto getActiveCart(HttpSession session)
	{
		String cartCode = (String) session.getAttribute(ApplicationConstant.CART_CODE);
		Optional<Cart> cartOptional = cartDao.findCartByCode(cartCode);
		if (!cartOptional.isPresent())
		{
			Cart newCart = new Cart();
			newCart.setCode(generateCode());
			cartDao.insertCart(newCart);
			session.setAttribute(ApplicationConstant.CART_CODE, newCart.getCode());
			return CartConverter.entityToDto(newCart);
		}
		Cart cart = cartOptional.get();
		final List<CartEntry> entries = cartEntryService.getEntriesByCartCode(cart.getCode());
		cart.setCartEntries(entries);
		return CartConverter.entityToDto(cart);
	}

	@Override
	public void deleteProductFromCart(final CustomerDto customerDto, final String productCode)
			throws IncorrectOperationException
	{
		final CartDto cartDto = getActiveCart(customerDto);
		cartEntryService.deleteEntry(cartDto, productCode);
	}

	@Override
	public void deleteProductFromAnonymousCart(final HttpSession session, final String productCode)
			throws IncorrectOperationException
	{
		final CartDto cartDto = getActiveCart(session);
		cartEntryService.deleteEntry(cartDto, productCode);
	}

	@Override
	public void updateProductQuantity(final CustomerDto customerDto, final int quantity, final String code)
			throws IncorrectOperationException
	{
		final CartDto cartDto = getActiveCart(customerDto);
		cartEntryService.updateEntryQuantity(code, quantity, cartDto);
	}

	@Override
	public void updateProductQuantityAnonymous(final HttpSession session, final int quantity, final String code)
			throws IncorrectOperationException
	{
		final CartDto cartDto = getActiveCart(session);
		cartEntryService.updateEntryQuantity(code, quantity, cartDto);
	}

	@Override
	public void placeOrder(final CartDto cartDto, final AddressDto addressDto)
	{
		final Address address = AddressConverter.dtoToEntity(addressDto);
		cartDto.setPlacedDate(new Date());
		final Cart cart = CartConverter.dtoToEntity(cartDto);
		addressDao.insertAddress(address);
		cart.setDeliveryAddress(address);
		cartDao.updateCart(cart);
	}

	@Override
	public Optional<CartDto> getCartByCode(final String cartCode)
	{
		final Optional<Cart> cartOptional = cartDao.findCartByCode(cartCode);
		if (cartOptional.isPresent())
		{
			final Cart cart = cartOptional.get();
			final List<CartEntry> entries = cartEntryService.getEntriesByCartCode(cart.getCode());
			cart.setCartEntries(entries);
		}
		return cartOptional.map(CartConverter::entityToDto);
	}

	@Override
	public List<CartDto> getCartsByEmail(final String email)
	{
		List<Cart> carts = cartDao.findCartsByEmail(email);
		for (Cart c : carts) {
			List<CartEntry> entries = cartEntryService.getEntriesByCartCode(c.getCode());
			c.setCartEntries(entries);
		}
		return CartConverter.castToDto(carts);
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
