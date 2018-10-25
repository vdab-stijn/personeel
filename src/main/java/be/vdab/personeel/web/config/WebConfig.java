package be.vdab.personeel.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
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
	
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
	}
}
