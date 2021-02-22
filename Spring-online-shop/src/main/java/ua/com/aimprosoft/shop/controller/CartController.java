package ua.com.aimprosoft.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.com.aimprosoft.shop.entities.Cart;
import ua.com.aimprosoft.shop.entities.CartEntry;
import ua.com.aimprosoft.shop.entities.Customer;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.SecurityService;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.converters.CartConverter;
import ua.com.aimprosoft.shop.util.converters.CartEntryConverter;


@Controller
public class CartController
{
	@Autowired
	private final SecurityService securityService;
	@Autowired
	private final CartService cartService;
	@Autowired
	private final CartEntryService cartEntryService;

	public CartController(final SecurityService securityService,
			final CartService cartService, final CartEntryService cartEntryService)
	{
		this.securityService = securityService;
		this.cartService = cartService;
		this.cartEntryService = cartEntryService;
	}

	@PostMapping("/add_product")
	public @ResponseBody
	String addToCart(@RequestParam("productCode") final String productCode,
			@RequestParam("quantity") final int quantity)
	{
		final Customer customer = securityService.getCurrentCustomer();
		cartService.addProductToCart(customer, productCode, quantity);
		return ApplicationConstant.SUCCESS_TEXT;
	}

	@GetMapping("/cart")
	public String showCart(final Model model)
	{
		final Customer customer = securityService.getCurrentCustomer();
		final Cart cart = cartService.getActiveCart(customer);
		final List<CartEntry> entries = cartEntryService.getEntriesByCartCode(cart.getCode());
		cart.setCartEntries(entries);
		final ua.com.aimprosoft.shop.dto.Cart cartDto = CartConverter.entityToDto(cart);
		model.addAttribute(ApplicationConstant.CART, cartDto);
		return "showCart";
	}

	@PostMapping("/delete_product")
	public String deleteProduct(@RequestParam("productCode") final String productCode)
	{
		try
		{
			final Customer customer = securityService.getCurrentCustomer();
			cartService.deleteProductFromCart(customer, productCode);
			return "redirect:/cart";
		}
		catch (final IncorrectOperationException e)
		{
			return "redirect:/cart";
		}
	}

	@PostMapping("/change_quantity")
	public String changeQuantity(@RequestParam("productCode") final String productCode,
			@RequestParam("quantity") final int quantity)
	{
		try
		{
			final Customer customer = securityService.getCurrentCustomer();
			cartService.updateProductQuantity(customer, quantity, productCode);
			return "redirect:/cart";
		}
		catch (final IncorrectOperationException e)
		{
			return "redirect:/cart";
		}
	}

	private List<ua.com.aimprosoft.shop.dto.CartEntry> castToDto(final List<CartEntry> entries)
	{
		final List<ua.com.aimprosoft.shop.dto.CartEntry> entriesDto = new ArrayList<>();
		for (final CartEntry entry : entries)
		{
			entriesDto.add(CartEntryConverter.entityToDto(entry));
		}
		return entriesDto;
	}
}
