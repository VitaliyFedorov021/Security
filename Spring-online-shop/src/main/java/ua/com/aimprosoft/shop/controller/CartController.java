package ua.com.aimprosoft.shop.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.aimprosoft.shop.exceptions.IncorrectOperationException;
import ua.com.aimprosoft.shop.models.Cart;
import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CartEntryService;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.service.SecurityService;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


@Controller
public class CartController
{
	private final SecurityService securityService;
	private final CustomerService customerService;
	private final CartService cartService;
	private final CartEntryService cartEntryService;

	@Autowired
	public CartController(final SecurityService securityService, final CustomerService customerService,
			final CartService cartService, final CartEntryService cartEntryService)
	{
		this.securityService = securityService;
		this.customerService = customerService;
		this.cartService = cartService;
		this.cartEntryService = cartEntryService;
	}

	@PostMapping("/add_product")
	public void addToCart(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String email = securityService.getCurrentUsername();
		Optional<Customer> optionalCustomer = customerService.getCustomerByEmail(email);
		Customer customer = optionalCustomer.get();
		String productCode = request.getParameter(ApplicationConstant.PRODUCT_CODE);
		int quantity = Integer.parseInt(request.getParameter(ApplicationConstant.QUANTITY));
		cartService.addProductToCart(customer, productCode, quantity);
		response.setContentType(ApplicationConstant.TYPE_TEXT);
		response.getWriter().write(ApplicationConstant.SUCCESS_TEXT);
	}

	@GetMapping("/cart")
	public String showCart(Model model) {
		String email = securityService.getCurrentUsername();
		Optional<Customer> optionalCustomer = customerService.getCustomerByEmail(email);
		Customer customer = optionalCustomer.get();
		Cart cart = cartService.getActiveCart(customer);
		List<CartEntry> entries = cartEntryService.getEntriesByCartCode(cart.getCode());
		cart.setCartEntries(entries);
		model.addAttribute(ApplicationConstant.CART, cart);
		return "showCart";
	}

	@PostMapping("/delete_product")
	public String deleteProduct(@RequestParam("productCode") String productCode) {
		try
		{
			String email = securityService.getCurrentUsername();
			Optional<Customer> optionalCustomer = customerService.getCustomerByEmail(email);
			Customer customer = optionalCustomer.get();
			cartService.deleteProductFromCart(customer, productCode);
			return "redirect:/cart";
		}
		catch (IncorrectOperationException e)
		{
			return "redirect:/cart";
		}
	}

	@PostMapping("/change_quantity")
	public String changeQuantity(@RequestParam("productCode") String productCode,
			@RequestParam("quantity") int quantity) {
		try
		{
			String email = securityService.getCurrentUsername();
			Optional<Customer> optionalCustomer = customerService.getCustomerByEmail(email);
			Customer customer = optionalCustomer.get();
			cartService.updateProductQuantity(customer, quantity, productCode);
			return "redirect:/cart";
		}
		catch (IncorrectOperationException e)
		{
			return "redirect:/cart";
		}
	}

}
