package be.vdab.personeel.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.personeel.entities.JobTitle;
import be.vdab.personeel.services.EmployeeService;
import be.vdab.personeel.services.JobTitleService;

@Controller
@RequestMapping("titles")
public class JobTitleController {

	private final JobTitleService jobTitleService;
	private final EmployeeService employeeService;
	
	public JobTitleController(
			final JobTitleService jobTitleService,
			final EmployeeService employeeService) {
		this.jobTitleService = jobTitleService;
		this.employeeService = employeeService;
	}
	
	private static final String VIEW_TITLES
	= "titles/title";
	@GetMapping
	public ModelAndView show() {
		return new ModelAndView(VIEW_TITLES)
				.addObject("titles", jobTitleService.findAll());
	}
	
	private static final String VIEW_TITLE
	= "titles/title";
	@GetMapping("{id}")
	public ModelAndView show(@PathVariable final long id) {
		final ModelAndView modelAndView = new ModelAndView(VIEW_TITLE);
		
		final Optional<JobTitle> jobTitle = jobTitleService.read(id);
		
		jobTitle.ifPresent(title -> {
			modelAndView.addObject("title", title);
			modelAndView.addObject(
					"employees", employeeService.findByJobTitle(title));});
		modelAndView.addObject("titles", jobTitleService.findAll());
		
		return modelAndView;
	}
}
