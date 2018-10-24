package be.vdab.personeel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.personeel.services.EmployeeService;

@Controller
@RequestMapping("employees")
public class EmployeeController {

	private final EmployeeService employeeService;
	
	public EmployeeController(final EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	private static final String VIEW_EMPLOYEES
	= "employees/employees";
	@GetMapping
	public ModelAndView show() {
		return new ModelAndView(VIEW_EMPLOYEES)
				.addObject(
						"employee", employeeService.findHighestRanking().get());
	}
}
