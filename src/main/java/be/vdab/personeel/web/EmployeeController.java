package be.vdab.personeel.web;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import be.vdab.personeel.web.session.EmployeeData;

@Controller
@RequestMapping("employees")
public class EmployeeController {

	private static final Logger LOGGER
	= LoggerFactory.getLogger(EmployeeController.class);
	
	private final EmployeeService employeeService;
	private final EmployeeData employeeData;
	
	public EmployeeController(
			final EmployeeService employeeService,
			final EmployeeData employeeData) {
		this.employeeService = employeeService;
		this.employeeData = employeeData;
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
					employeeService.findSubordinates(e)); });
		
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
	
	private static final String REDIRECT_VIEW_SSN
	= "redirect:/employees/ssn";
	@GetMapping("{id}/ssn")
	public String showSSN(@PathVariable final long id) {
		employeeData.setEmployeeId(id);
		
		return REDIRECT_VIEW_SSN;
	}
	
	private static final String VIEW_SSN
	= "employees/ssn";
	@GetMapping("/ssn")
	public ModelAndView showSSN() {
		final long id = employeeData.getEmployeeId();
		
		LOGGER.debug("=======================================================");
		LOGGER.debug("VIEW: " + VIEW_SSN + " (PathVariable 'id': " + id + ")");
		
		final ModelAndView modelAndView = new ModelAndView(VIEW_SSN);
		
		final Optional<Employee> employee = employeeService.read(id);
		
		employee.ifPresent(e -> {
			modelAndView.addObject("socialSecurityNumberForm",
					new SocialSecurityNumberForm(
					e, e.getSocialSecurityNumber()));
			modelAndView.addObject("employee", e); });
		
		return modelAndView;
	}
	
	private static final String REDIRECT_AFTER_SAVE_SSN
	= "redirect:/employees/{id}";
	// IF FORM VALIDATION FAILS: DON'T REDIRECT, RETURN THE TEMPLATE
	private static final String REDIRECT_AFTER_SAVE_SSN_FAILS
	= VIEW_SSN;
	@PostMapping("/ssn")
	public String saveSSN(
			// ERRORS ARE BOUND TO socialSecurityNumberForm
			@Valid final SocialSecurityNumberForm form,
			final BindingResult bindingResult,
			final RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			LOGGER.error("SSN form has binding errors:");
			LOGGER.debug(bindingResult.getTarget().getClass().getName());
			LOGGER.debug(bindingResult.getObjectName());
			bindingResult.getAllErrors().stream().forEach(e -> 
				LOGGER.error(e.toString()));
			
			return REDIRECT_AFTER_SAVE_SSN_FAILS;
		}
		
		redirectAttributes.addAttribute(
				"id", form.getEmployee().getId());
		
		LOGGER.debug("SSN FORM: " +
			form.getEmployee().getId() +
			" (" +
				form.getSocialSecurityNumber().toString() +
			")");
		
		employeeService.saveSSN(
			form.getEmployee().getId(),
			form.getSocialSecurityNumber());
		
		return REDIRECT_AFTER_SAVE_SSN;
	}
}
