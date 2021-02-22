package ua.com.aimprosoft.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
		final List<Product> products = productService.getAllByCategoryCode(categoryCode);
		final List<ua.com.aimprosoft.shop.dto.Product> productsDto = castToDto(products);
		model.addAttribute(ApplicationConstant.PRODUCTS, productsDto);
		return "plp";
	}

	@GetMapping("/product/{productCode}")
	public String productDetailsPage(@PathVariable final String productCode, final Model model)
	{
		final Product product = productService.getByProductCode(productCode);
		final ua.com.aimprosoft.shop.dto.Product productDto = ProductConverter.entityToDto(product);
		model.addAttribute(ApplicationConstant.PRODUCT, productDto);
		return "pdp";
	}

	private List<ua.com.aimprosoft.shop.dto.Product> castToDto(final List<Product> products)
	{
		final List<ua.com.aimprosoft.shop.dto.Product> productsDto = new ArrayList<>();
		for (final Product p : products)
		{
			productsDto.add(ProductConverter.entityToDto(p));
		}
		return productsDto;
	}
}
