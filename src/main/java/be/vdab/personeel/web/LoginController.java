package be.vdab.personeel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {

	private static final String VIEW_LOGIN
	= "login";
	@GetMapping
	public String login() {
		return VIEW_LOGIN;
	}
}
