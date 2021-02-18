package ua.com.aimprosoft.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.service.SecurityService;
import ua.com.aimprosoft.shop.util.Extractor;
import ua.com.aimprosoft.shop.util.Validator;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


@Controller
public class RegistrationController
{
	private final CustomerService customerService;
	private final Extractor<Customer> extractor;
	private final Validator<Customer> validator;
	private final SecurityService securityService;

	@Autowired
	public RegistrationController(final CustomerService customerService,
			final Extractor<Customer> extractor,
			final Validator<Customer> validator, final SecurityService securityService)
	{
		this.customerService = customerService;
		this.extractor = extractor;
		this.validator = validator;
		this.securityService = securityService;
	}

	@GetMapping("/sign_up")
	public String signUpPage()
	{
		return "signUpPage";
	}

	@PostMapping("/sign_up")
	public String signUp(HttpServletRequest request, Model model)
	{
		Customer customer = extractor.map(request);
		List<String> exceptions = new ArrayList<>();
		validator.validate(customer, exceptions);
		if (exceptions.size() > 0)
		{
			model.addAttribute(ApplicationConstant.MESSAGE, exceptions);
			return "signUpPage";
		}
		customerService.registerCustomer(customer);
		securityService.autoLogin(customer.getEmail(), customer.getPassword());
		return "redirect:/";
	}
}
