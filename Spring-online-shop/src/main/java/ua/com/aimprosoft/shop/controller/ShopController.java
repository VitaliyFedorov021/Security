package ua.com.aimprosoft.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ShopController
{
	@GetMapping("/")
	public String homePage()
	{
		return "home";
	}
}
