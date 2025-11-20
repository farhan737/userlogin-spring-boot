package userlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
	public String registerPage(@RequestParam(value = "status", required = false) String status, Model model) {
		model.addAttribute("status", status);
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

	@GetMapping("/login")
	public String loginPage(@RequestParam(value = "status", required = false) String status, Model model) {
		model.addAttribute("status", status);
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@ModelAttribute User user, HttpSession session) {
		if (service.isAuthorised(user)) {
			session.setAttribute("authorisedUserEmail", user.getEmail());
			return "redirect:/dashboard";
		} else
			return "redirect:/login?status=invalidcredentials";
	}

	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, HttpServletResponse response, Model model) {
		String email = (String) session.getAttribute("authorisedUserEmail");
		if (email == null)
			return "redirect:/login?status=sessionexpired";
		User user = service.findByEmail(email);
		model.addAttribute("user", user);
		return "dashboard";
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login?status=logoutsuccessfull";
	}
}
