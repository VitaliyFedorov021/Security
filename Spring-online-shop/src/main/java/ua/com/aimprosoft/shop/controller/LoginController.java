package ua.com.aimprosoft.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.constant.ErrorConstant;


@Controller
public class LoginController
{
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/login-fail")
	public String failLogin(final Model model)
	{
		model.addAttribute(ApplicationConstant.MESSAGE, ErrorConstant.INCORRECT_LOGIN_DATA);
		return "login";
	}
}
