package be.vdab.personeel.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.personeel.entities.Employee;
import be.vdab.personeel.services.EmployeeService;
import be.vdab.personeel.web.forms.EmployeeRaiseForm;
import be.vdab.personeel.web.forms.SocialSecurityNumberForm;

@Controller
@RequestMapping("employees")
public class EmployeeController {

	private final EmployeeService employeeService;
	
	public EmployeeController(final EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	private static final String VIEW_EMPLOYEE
	= "employees/employee";
	@GetMapping
	public ModelAndView show() {
		return showById(0);
	}
	
	@GetMapping("{id}")
	public ModelAndView show(
			@PathVariable final long id) {
		return showById(id);
	}
	
	private ModelAndView showById(final long id) {
		final ModelAndView modelAndView = new ModelAndView(VIEW_EMPLOYEE);
		final Optional<Employee> employee = id == 0 ?
				employeeService.findHighestRanking() :
				employeeService.read(id);
		
		employee.ifPresent(e -> {
			modelAndView.addObject("employee", e);
			modelAndView.addObject("subordinates",
					employeeService.findSubordinates(e));});
		
		return modelAndView;
	}
	
	private static final String VIEW_RAISE
	= "employees/raise";
	@GetMapping("{id}/raise")
	public ModelAndView raise(@PathVariable final long id) {
		return new ModelAndView(VIEW_RAISE)
					.addObject(new EmployeeRaiseForm())
					.addObject("employee", employeeService.read(id).get());
	}
	
	private static final String REDIRECT_AFTER_RAISE
	= "redirect:/employees/{id}";
	@PostMapping("{id}/raise")
	public String raiseSalary(
			@PathVariable final long id,
			@Valid final EmployeeRaiseForm form,
			final BindingResult bindingResult,
			final RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors())
			return REDIRECT_AFTER_RAISE;
		
		employeeService.raiseSalary(id, form.getRaise());
		
		redirectAttributes.addAttribute("id", id);
		
		return REDIRECT_AFTER_RAISE;
	}
	
	private static final String VIEW_SSN
	= "employees/socialsecuritynumber";
	@GetMapping("{id}/ssn")
	public ModelAndView showSSN(@PathVariable final long id) {
		return new ModelAndView(VIEW_SSN)
				.addObject(new SocialSecurityNumberForm())
				.addObject("employee", employeeService.read(id).get());
	}
	
	private static final String REDIRECT_AFTER_SAVE_SSN
	= "redirect:/employees/{id}";
	@PostMapping("{id}/ssn")
	public String saveSSN(
			@PathVariable final long id,
			@Valid final SocialSecurityNumberForm form,
			final BindingResult bindingResult,
			final RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors() )
			return REDIRECT_AFTER_SAVE_SSN;
		
		employeeService.saveSSN(id, form.getSocialSecurityNumber());
		
		redirectAttributes.addAttribute("id", id);
		
		return REDIRECT_AFTER_SAVE_SSN;
	}
}
