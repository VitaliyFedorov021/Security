package ua.com.aimprosoft.shop.controller;

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
			@RequestParam("quantity") final int quantity)
	{
		final CustomerDto customerDto = securityService.getCurrentCustomer();
		cartService.addProductToCart(customerDto, productCode, quantity);
		return ApplicationConstant.SUCCESS_TEXT;
	}

	@GetMapping("/cart")
	public String showCart(final Model model)
	{
		final CustomerDto customerDto = securityService.getCurrentCustomer();
		final CartDto cartDto = cartService.getActiveCart(customerDto);
		model.addAttribute(ApplicationConstant.CART, cartDto);
		return "cart";
	}

	@PostMapping("/delete_product")
	public String deleteProduct(@RequestParam("productCode") final String productCode)
	{
		try
		{
			final CustomerDto customerDto = securityService.getCurrentCustomer();
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
			@RequestParam("quantity") final int quantity)
	{
		try
		{
			final CustomerDto customerDto = securityService.getCurrentCustomer();
			cartService.updateProductQuantity(customerDto, quantity, productCode);
			return "redirect:/cart";
		}
		catch (final IncorrectOperationException e)
		{
			return "redirect:/cart";
		}
	}
}
