package ua.com.aimprosoft.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.aimprosoft.shop.dto.CartDto;
import ua.com.aimprosoft.shop.dto.CustomerDto;
import ua.com.aimprosoft.shop.entities.Customer;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.SecurityService;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


@Controller
public class ShopController
{
	@Autowired
	private final CartService cartService;
	@Autowired
	private final SecurityService securityService;

	public ShopController(final CartService cartService,
			final SecurityService securityService)
	{
		this.cartService = cartService;
		this.securityService = securityService;
	}

	@GetMapping("/")
	public String homePage()
	{
		return "home";
	}

	@GetMapping("/info")
	public String infoPage(Model model) {
		final CustomerDto customer = securityService.getCurrentCustomer();
		final List<CartDto> carts = cartService.getCartsByEmail(customer.getEmail());
		model.addAttribute(ApplicationConstant.CARTS, carts);
		return "userInfo";
	}
}
