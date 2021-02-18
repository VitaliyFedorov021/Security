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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ua.com.aimprosoft.shop.models.Category;
import ua.com.aimprosoft.shop.models.Customer;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.service.CartService;
import ua.com.aimprosoft.shop.service.CategoryService;
import ua.com.aimprosoft.shop.service.CustomerService;
import ua.com.aimprosoft.shop.service.ProductService;
import ua.com.aimprosoft.shop.service.SecurityService;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


@Controller
public class ShopController
{
	private final CategoryService categoryService;
	private final ProductService productService;

	@Autowired
	public ShopController(final CategoryService categoryService, final ProductService productService)
	{
		this.categoryService = categoryService;
		this.productService = productService;
	}

	@GetMapping("/")
	public String homePage()
	{
		return "homePage";
	}

	@GetMapping("/categories")
	public String categoriesPage(Model model)
	{
		List<Category> categories = categoryService.getAll();
		model.addAttribute(ApplicationConstant.CATEGORIES, categories);
		return "categoriesPage";
	}

	@GetMapping("/products/{categoryCode}")
	public String productsPage(@PathVariable String categoryCode, Model model)
	{
		List<Product> products = productService.getAllByCategoryCode(categoryCode);
		model.addAttribute(ApplicationConstant.PRODUCTS, products);
		return "plp";
	}

	@GetMapping("/product/{productCode}")
	public String productDetailsPage(@PathVariable String productCode, Model model)
	{
		Product product = productService.getByProductCode(productCode);
		model.addAttribute(ApplicationConstant.PRODUCT, product);
		return "pdp";
	}
}
