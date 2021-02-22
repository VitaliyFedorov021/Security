package ua.com.aimprosoft.shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ua.com.aimprosoft.shop.entities.Cart;
import ua.com.aimprosoft.shop.entities.CartEntry;
import ua.com.aimprosoft.shop.entities.Customer;
import ua.com.aimprosoft.shop.forms.Address;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.SecurityService;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.converters.AddressConverter;
import ua.com.aimprosoft.shop.util.converters.CartConverter;
import ua.com.aimprosoft.shop.util.converters.CustomerConverter;


@Controller
public class OrderController
{
	@Autowired
	private final CartService cartService;
	@Autowired
	private final SecurityService securityService;
	@Autowired
	private final CartEntryService cartEntryService;
	@Autowired
	@Qualifier("addressValidator")
	private final Validator validator;

	public OrderController(final CartService cartService, final SecurityService securityService,
			final CartEntryService cartEntryService,
			@Qualifier("addressValidator") final Validator validator)
	{
		this.cartService = cartService;
		this.securityService = securityService;
		this.cartEntryService = cartEntryService;
		this.validator = validator;
	}

	@GetMapping("/place_order")
	public String placeOrderPage(final Model model)
	{
		final Customer customer = securityService.getCurrentCustomer();
		final Cart cartEntity = cartService.getActiveCart(customer);
		final List<CartEntry> cartEntries = cartEntryService.getEntriesByCartCode(cartEntity.getCode());
		if (cartEntries.size() == 0)
		{
			return "redirect:/cart";
		}
		cartEntity.setCartEntries(cartEntries);
		final ua.com.aimprosoft.shop.dto.Cart cartDto = CartConverter.entityToDto(cartEntity);
		final ua.com.aimprosoft.shop.dto.Customer customerDto = CustomerConverter.entityToDto(customer);
		model.addAttribute(ApplicationConstant.CART, cartDto);
		model.addAttribute(ApplicationConstant.CUSTOMER, customerDto);
		model.addAttribute(ApplicationConstant.ADDRESS, new Address());
		return "checkout";
	}

	@PostMapping("/confirm_order")
	public String saveDeliveryAddress(@ModelAttribute("address") final Address address,
			final BindingResult bindingResult)
	{
		final Customer customer = securityService.getCurrentCustomer();
		final ua.com.aimprosoft.shop.entities.Address addressEntity = AddressConverter.formToEntity(address);
		validator.validate(addressEntity, bindingResult);
		if (bindingResult.hasErrors())
		{
			return "checkout";
		}
		final Cart cart = cartService.getActiveCart(customer);
		cartService.placeOrder(cart, addressEntity);
		return "redirect:/confirm_order/" + cart.getCode();
	}

	@GetMapping("/confirm_order/{cartCode}")
	public String confirmationPage(@PathVariable final String cartCode, final Model model)
	{
		final Optional<Cart> cartOptional = cartService.getCartByCode(cartCode);
		if (!cartOptional.isPresent())
		{
			return "redirect:/";
		}
		final Cart cartEntity = cartOptional.get();
		final List<CartEntry> entries = cartEntryService.getEntriesByCartCode(cartCode);
		if (entries.size() == 0)
		{
			return "redirect:/cart";
		}
		cartEntity.setCartEntries(entries);
		final ua.com.aimprosoft.shop.dto.Cart cartDto = CartConverter.entityToDto(cartEntity);
		model.addAttribute(ApplicationConstant.CART, cartDto);
		return "confirmationPage";
	}
}
