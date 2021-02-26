package ua.com.aimprosoft.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ua.com.aimprosoft.shop.dto.ProductDto;
import ua.com.aimprosoft.shop.entities.Product;
import ua.com.aimprosoft.shop.service.ProductService;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;
import ua.com.aimprosoft.shop.util.converters.ProductConverter;


@Controller
public class ProductController
{
	@Autowired
	private final ProductService productService;

	public ProductController(final ProductService productService)
	{
		this.productService = productService;
	}

	@GetMapping("/products/{categoryCode}")
	public String productsPage(@PathVariable final String categoryCode, final Model model)
	{
		final List<ProductDto> productsDto = productService.getAllByCategoryCode(categoryCode);
		model.addAttribute(ApplicationConstant.PRODUCTS, productsDto);
		return "products";
	}

	@GetMapping("/product/{productCode}")
	public String productDetailsPage(@PathVariable final String productCode, final Model model)
	{
		final Product product = productService.getByProductCode(productCode);
		final ProductDto productDto = ProductConverter.entityToDto(product);
		model.addAttribute(ApplicationConstant.PRODUCT, productDto);
		return "product";
	}
}
