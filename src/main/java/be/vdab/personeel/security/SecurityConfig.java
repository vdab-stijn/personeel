package be.vdab.personeel.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String AUTH_PRESIDENT = "President";
	private static final String AUTH_EMEA = "Sale Manager (EMEA)";
	private static final String AUTH_APAC = "Sales Manager (APAC)";
	private static final String AUTH_NA = "Sales Manager (NA)";
	private static final String AUTH_SALES = "Sales Rep";
	private static final String AUTH_MARKETING_VP = "VP Marketing";
	private static final String AUTH_SALES_VP = "VP Sales";
	
	private static final String USERS_BY_USERNAME
	= "SELECT email AS username, paswoord AS password " +
			"FROM werknemers WHERE email = ?";
	private static final String AUTHORITIES_BY_USERNAME
	= "SELECTwerknemers.email AS username, jobtitels.naam AS authorities " +
			"FROM werknemers INNER JOIN jobtitels " +
			"ON jobtitels.id = werknemers.jobtitelid " +
			"WHERE werknemers.email = ?";
	
	@Bean
	public JdbcDaoImpl jdbcDaoImpl(final DataSource dataSource) {
		final JdbcDaoImpl impl = new JdbcDaoImpl();
		
		impl.setDataSource(dataSource);
		impl.setUsersByUsernameQuery(USERS_BY_USERNAME);
		impl.setAuthoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME);
		
		return impl;
	}
	
	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring()
			.mvcMatchers("/images/**")
			.mvcMatchers("/css/**")
			.mvcMatchers("/js/**");
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage("/login")
			.and().logout()
			.and().authorizeRequests()
			.mvcMatchers("/**").permitAll();/*
			.mvcMatchers("/tenders/add").hasAuthority(MANAGER)
			.mvcMatchers("/employees")
				.hasAnyAuthority(STOCKKEEPER, HELPDESK)
			.mvcMatchers("/", "/branches/**", "/login").permitAll()
			.mvcMatchers("/**").authenticated();*/
		
		http.httpBasic();
	}
}
