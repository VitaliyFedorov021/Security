package ua.com.aimprosoft.shop.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.aimprosoft.shop.security.SecurityCustomer;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


@Controller
public class ShopController
{
	@GetMapping("/")
	public String homePage(final Model model)
	{
		if (!(SecurityContextHolder.getContext()
				.getAuthentication() instanceof AnonymousAuthenticationToken))
		{
			final SecurityCustomer securityCustomer = (SecurityCustomer) SecurityContextHolder.getContext()
					.getAuthentication()
					.getPrincipal();
			model.addAttribute(ApplicationConstant.FIRST_NAME, securityCustomer.getCustomer().getFirstName());
			model.addAttribute(ApplicationConstant.LAST_NAME, securityCustomer.getCustomer().getLastName());
		}
		return "homePage";
	}
}
