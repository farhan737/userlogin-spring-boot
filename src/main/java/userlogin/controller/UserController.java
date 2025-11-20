package userlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import userlogin.model.User;
import userlogin.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/")
	public String landing() {
		return "landing";
	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user) {
		if (service.register(user))
			return "redirect:/login";
		else
			return "redirect:/register?status=userexists";
	}
}
