package ua.com.aimprosoft.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.com.aimprosoft.shop.forms.Customer;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.service.SecurityService;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.converters.CustomerConverter;


@Controller
public class RegistrationController
{
	private final CustomerService customerService;
	private final Validator validator;
	private final SecurityService securityService;

	@Autowired
	public RegistrationController(final CustomerService customerService,
			@Qualifier("customerValidator") final Validator validator,
			final SecurityService securityService)
	{
		this.customerService = customerService;
		this.validator = validator;
		this.securityService = securityService;
	}

	@GetMapping("/sign_up")
	public String signUpPage(final Model model)
	{
		model.addAttribute(ApplicationConstant.CUSTOMER, new Customer());
		return "signUpPage";
	}

	@PostMapping("/sign_up")
	public String signUp(@ModelAttribute("customer") final Customer customer,
			final BindingResult bindingResult)
	{
		validator.validate(customer, bindingResult);
		if (bindingResult.hasErrors())
		{
			return "signUpPage";
		}
		final ua.com.aimprosoft.shop.entities.Customer customerEntity = CustomerConverter.formToEntity(customer);
		customerService.registerCustomer(customerEntity);
		securityService.autoLogin(customerEntity.getEmail(), customerEntity.getPassword());
		return "redirect:/";
	}
}