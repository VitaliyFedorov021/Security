package ua.com.aimprosoft.shop.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

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
import ua.com.aimprosoft.shop.forms.CustomerForm;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.SecurityService;
import ua.com.aimprosoft.shop.util.MailSender;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.converters.AddressConverter;
import ua.com.aimprosoft.shop.util.converters.CustomerConverter;


@Controller
public class OrderController
{
	@Autowired
	private final CartService cartService;
	@Autowired
	private final SecurityService securityService;
	@Autowired
	private final Validator addressValidator;
	@Autowired
	private final MailSender mailSender;

	public OrderController(final CartService cartService, final SecurityService securityService,
			@Qualifier("addressValidator") final Validator addressValidator,
			final MailSender mailSender)
	{
		this.cartService = cartService;
		this.securityService = securityService;
		this.addressValidator = addressValidator;
		this.mailSender = mailSender;
	}

	@GetMapping("/place_order")
	public String placeOrderPage(final Model model, final HttpSession session)
	{
		CustomerDto customerDto = securityService.getCurrentCustomer();
		final CartDto cartDto = getCart(customerDto, session);
		if (customerDto == null) {
			customerDto = (CustomerDto) session.getAttribute(ApplicationConstant.CUSTOMER);
		}
		if (cartDto.getCartEntries().size() == 0)
		{
			return "redirect:/cart";
		}
		model.addAttribute(ApplicationConstant.CART, cartDto);
		model.addAttribute(ApplicationConstant.CUSTOMER, customerDto);
		model.addAttribute(ApplicationConstant.ADDRESS, new AddressForm());
		return "placeOrder";
	}

	@PostMapping("/confirm_order")
	public String saveDeliveryAddress(@ModelAttribute("address") final AddressForm addressForm,
			final BindingResult bindingResult,
			final HttpSession session)
	{
		CustomerDto customerDto = securityService.getCurrentCustomer();
		final CartDto cartDto = getCart(customerDto, session);
		if (customerDto == null) {
			customerDto = (CustomerDto) session.getAttribute(ApplicationConstant.CUSTOMER);
		}
		addressValidator.validate(addressForm, bindingResult);
		if (bindingResult.hasErrors())
		{
			return "placeOrder";
		}
		final AddressDto addressDto = AddressConverter.formToDto(addressForm);
		cartService.placeOrder(cartDto, addressDto);
		mailSender.sendOrderConfirmationEmail(customerDto.getEmail(), cartDto, addressDto);
		clearSession(session);
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
		return "confirmation";
	}

	@GetMapping("/personal_data")
	public String personalDataInput(final Model model)
	{
		model.addAttribute(ApplicationConstant.CUSTOMER, new CustomerForm());
		return "personalData";
	}

	@PostMapping("/personal_data")
	public String personalDataSave(@ModelAttribute("customer") final CustomerForm customerForm,
			final HttpSession session)
	{
		final CustomerDto customerDto = CustomerConverter.formToDto(customerForm);
		session.setAttribute(ApplicationConstant.CUSTOMER, customerDto);
		return "redirect:/place_order";
	}

	private CartDto getCart(CustomerDto customerDto, final HttpSession session)
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

	private void clearSession(HttpSession session)
	{
		if (session != null)
		{
			session.removeAttribute(ApplicationConstant.CART_CODE);
			session.removeAttribute(ApplicationConstant.CUSTOMER);
		}
	}
}
