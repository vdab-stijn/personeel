package be.vdab.personeel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

	public IndexController() {
		
	}
	
	private static final String VIEW_INDEX
	= "index";
	@GetMapping
	public ModelAndView index() {
		return new ModelAndView(VIEW_INDEX);
	}
}
