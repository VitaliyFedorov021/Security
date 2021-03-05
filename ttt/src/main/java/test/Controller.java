package test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class Controller
{
	private final DAO dao;

	@Autowired
	public Controller(final DAO dao)
	{
		this.dao = dao;
	}

	@GetMapping("/")
	public String index(Model model) {
		List<Customer> customers = dao.findAll();
		model.addAttribute("list", customers);
		return "home";
	}

}
