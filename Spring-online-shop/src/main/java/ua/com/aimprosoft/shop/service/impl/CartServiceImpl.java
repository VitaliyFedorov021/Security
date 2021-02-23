package ua.com.aimprosoft.shop.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.aimprosoft.shop.dao.AddressDao;
import ua.com.aimprosoft.shop.dao.CartDao;
import ua.com.aimprosoft.shop.dto.CartDto;
import ua.com.aimprosoft.shop.dto.CustomerDto;
import ua.com.aimprosoft.shop.entities.Address;
import ua.com.aimprosoft.shop.entities.Cart;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;
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
		return CartConverter.entityToDto(cartOptional.get());
	}

	@Override
	public void deleteProductFromCart(final CustomerDto customerDto, final String productCode)
			throws IncorrectOperationException
	{
		final CartDto cartDto = getActiveCart(customerDto);
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
	public void placeOrder(final CartDto cartDto, final Address address)
	{
		final Cart cart = CartConverter.dtoToEntity(cartDto);
		addressDao.insertAddress(address);
		cart.setDeliveryAddress(address);
		cart.setPlacedDate(new Date());
		cartDao.updateCart(cart);
	}

	@Override
	public Optional<CartDto> getCartByCode(final String cartCode)
	{
		final Optional<Cart> cartOptional = cartDao.findCartByCode(cartCode);
		return cartOptional.map(CartConverter::entityToDto);
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
