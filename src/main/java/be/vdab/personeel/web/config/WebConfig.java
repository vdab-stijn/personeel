package be.vdab.personeel.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import be.vdab.personeel.converters.IdToEmployeeConverter;
import be.vdab.personeel.converters.StringToSocialSecurityNumberConverter;
import be.vdab.personeel.services.EmployeeService;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

//	@Bean
//	FixedLocaleResolver localeResolver() {
//		return new FixedLocaleResolver(new Locale("fr", "BE"));
//	}
	
//	@Bean
//	SessionLocaleResolver localeResolver() {
//		return new SessionLocaleResolver();
//	}
	
	@Bean
	CookieLocaleResolver localeResolver() {
		final CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieMaxAge(604_800);
		
		return resolver;
	}
	
	@Autowired
	private EmployeeService employeeService;
	
	private final EmployeeService employeeService() {
		return employeeService;
	}
	
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
	}
	
	@Override
	public void addFormatters(final FormatterRegistry registry) {
		registry.addConverter(new StringToSocialSecurityNumberConverter());
		registry.addConverter(new IdToEmployeeConverter(employeeService()));
	}
}
