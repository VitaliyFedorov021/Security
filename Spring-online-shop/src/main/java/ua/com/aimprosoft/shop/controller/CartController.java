package ua.com.aimprosoft.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.com.aimprosoft.shop.dto.CartDto;
import ua.com.aimprosoft.shop.dto.CustomerDto;
import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.SecurityService;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


@Controller
public class CartController
{
	@Autowired
	private final SecurityService securityService;
	@Autowired
	private final CartService cartService;

	public CartController(final SecurityService securityService,
			final CartService cartService)
	{
		this.securityService = securityService;
		this.cartService = cartService;
	}

	@PostMapping("/add_product")
	public @ResponseBody
	String addToCart(@RequestParam("productCode") final String productCode,
			@RequestParam("quantity") final int quantity,
			final HttpSession session)
	{
		final CustomerDto customerDto = securityService.getCurrentCustomer();
		if (customerDto == null)
		{
			cartService.addProductToAnonymousCart(session, productCode, quantity);
			return ApplicationConstant.SUCCESS_TEXT;
		}
		cartService.addProductToCart(customerDto, productCode, quantity);
		return ApplicationConstant.SUCCESS_TEXT;
	}

	@GetMapping("/cart")
	public String showCart(final Model model, final HttpSession session)
	{
		final CustomerDto customerDto = securityService.getCurrentCustomer();
		final CartDto cartDto = getCart(customerDto, session);
		model.addAttribute(ApplicationConstant.CART, cartDto);
		return "cart";
	}

	@PostMapping("/delete_product")
	public String deleteProduct(@RequestParam("productCode") final String productCode, final HttpSession session)
	{
		try
		{
			final CustomerDto customerDto = securityService.getCurrentCustomer();
			if (customerDto == null)
			{
				cartService.deleteProductFromAnonymousCart(session, productCode);
				return "redirect:/cart";
			}
			cartService.deleteProductFromCart(customerDto, productCode);
			return "redirect:/cart";
		}
		catch (final IncorrectOperationException e)
		{
			return "redirect:/cart";
		}
	}

	@PostMapping("/change_quantity")
	public String changeQuantity(@RequestParam("productCode") final String productCode,
			@RequestParam("quantity") final int quantity, final HttpSession session)
	{
		try
		{
			final CustomerDto customerDto = securityService.getCurrentCustomer();
			if (customerDto == null)
			{
				cartService.updateProductQuantityAnonymous(session, quantity, productCode);
				return "redirect:/cart";
			}
			cartService.updateProductQuantity(customerDto, quantity, productCode);
			return "redirect:/cart";
		}
		catch (final IncorrectOperationException e)
		{
			return "redirect:/cart";
		}
	}

	private CartDto getCart(final CustomerDto customerDto, final HttpSession session)
	{
		CartDto cartDto = null;
		if (customerDto == null)
		{
			cartDto = cartService.getActiveCart(session);
		}
		else
		{
			cartDto = cartService.getActiveCart(customerDto);
		}
		return cartDto;
	}
}
