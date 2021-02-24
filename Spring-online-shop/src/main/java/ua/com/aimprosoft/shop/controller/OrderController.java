package ua.com.aimprosoft.shop.controller;

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

import ua.com.aimprosoft.shop.dto.AddressDto;
import ua.com.aimprosoft.shop.dto.CartDto;
import ua.com.aimprosoft.shop.dto.CustomerDto;
import ua.com.aimprosoft.shop.forms.AddressForm;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.SecurityService;
import ua.com.aimprosoft.shop.util.MailSender;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.converters.AddressConverter;


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
	@Autowired
	private final MailSender mailSender;

	public OrderController(final CartService cartService, final SecurityService securityService,
			final CartEntryService cartEntryService,
			@Qualifier("addressValidator") final Validator validator,
			final MailSender mailSender)
	{
		this.cartService = cartService;
		this.securityService = securityService;
		this.cartEntryService = cartEntryService;
		this.validator = validator;
		this.mailSender = mailSender;
	}

	@GetMapping("/place_order")
	public String placeOrderPage(final Model model)
	{
		final CustomerDto customerDto = securityService.getCurrentCustomer();
		final CartDto cartDto = cartService.getActiveCart(customerDto);
		if (cartDto.getCartEntries().size() == 0)
		{
			return "redirect:/cart";
		}
		model.addAttribute(ApplicationConstant.CART, cartDto);
		model.addAttribute(ApplicationConstant.CUSTOMER, customerDto);
		model.addAttribute(ApplicationConstant.ADDRESS, new AddressForm());
		return "checkout";
	}

	@PostMapping("/confirm_order")
	public String saveDeliveryAddress(@ModelAttribute("address") final AddressForm addressForm,
			final BindingResult bindingResult)
	{
		final CustomerDto customerDto = securityService.getCurrentCustomer();
		validator.validate(addressForm, bindingResult);
		if (bindingResult.hasErrors())
		{
			return "checkout";
		}
		final AddressDto addressDto = AddressConverter.formToDto(addressForm);
		final CartDto cartDto = cartService.getActiveCart(customerDto);
		cartService.placeOrder(cartDto, addressDto);
		mailSender.sendOrderConfirmationEmail(customerDto.getEmail(), cartDto, addressDto);
		return "redirect:/confirm_order/" + cartDto.getCode();
	}

	@GetMapping("/confirm_order/{cartCode}")
	public String confirmationPage(@PathVariable final String cartCode, final Model model)
	{
		final Optional<CartDto> cartDtoOptional = cartService.getCartByCode(cartCode);
		if (!cartDtoOptional.isPresent())
		{
			return "redirect:/";
		}
		final CartDto cartDto = cartDtoOptional.get();
		if (cartDto.getCartEntries().size() == 0)
		{
			return "redirect:/cart";
		}
		model.addAttribute(ApplicationConstant.CART, cartDto);
		return "confirmationPage";
	}
}
