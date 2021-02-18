package ua.com.aimprosoft.shop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ua.com.aimprosoft.shop.models.Address;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.service.SecurityService;
import ua.com.aimprosoft.shop.util.Validator;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


@Controller
public class OrderController
{
	private final CartService cartService;
	private final SecurityService securityService;
	private final CustomerService customerService;
	private final CartEntryService cartEntryService;
	private final Validator<Address> validator;

	@Autowired
	public OrderController(final CartService cartService, final SecurityService securityService,
			final CustomerService customerService, final CartEntryService cartEntryService,
			final Validator<Address> validator)
	{
		this.cartService = cartService;
		this.securityService = securityService;
		this.customerService = customerService;
		this.cartEntryService = cartEntryService;
		this.validator = validator;
	}

	@GetMapping("/place_order")
	public String placeOrderPage(Model model)
	{
		String email = securityService.getCurrentUsername();
		Optional<Customer> optionalCustomer = customerService.getCustomerByEmail(email);
		Customer customer = optionalCustomer.get();
		final Cart cart = cartService.getActiveCart(customer);
		final List<CartEntry> cartEntries = cartEntryService.getEntriesByCartCode(cart.getCode());
		if (cartEntries.size() == 0)
		{
			return "redirect:/cart";
		}
		cart.setCartEntries(cartEntries);
		model.addAttribute(ApplicationConstant.CART, cart);
		model.addAttribute(ApplicationConstant.CUSTOMER, customer);
		return "checkout";
	}

	@PostMapping("/confirm_order")
	public String saveDeliveryAddress(@ModelAttribute("address") Address address, Model model)
	{
		String email = securityService.getCurrentUsername();
		Optional<Customer> optionalCustomer = customerService.getCustomerByEmail(email);
		Customer customer = optionalCustomer.get();
		List<String> exceptions = new ArrayList<>();
		validator.validate(address, exceptions);
		if (exceptions.size() > 0) {
			model.addAttribute(ApplicationConstant.MESSAGE, exceptions);
			return "redirect:/place_order";
		}
		Cart cart = cartService.getActiveCart(customer);
		cartService.placeOrder(cart, address);
		return "redirect:/confirm_order/" + cart.getCode();
	}

	@GetMapping("/confirm_order/{cartCode}")
	public String confirmationPage(@PathVariable String cartCode, Model model) {
		Optional<Cart> cartOptional = cartService.getCartByCode(cartCode);
		if (!cartOptional.isPresent()) {
			return "redirect:/";
		}
		Cart cart = cartOptional.get();
		List<CartEntry> entries = cartEntryService.getEntriesByCartCode(cartCode);
		if (entries.size() == 0) {
			return "redirect:/cart";
		}
		cart.setCartEntries(entries);
		model.addAttribute(ApplicationConstant.CART, cart);
		return "confirmationPage";
	}
}
