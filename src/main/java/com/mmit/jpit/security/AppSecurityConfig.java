package com.mmit.jpit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DaoAuthenticationProvider daoauth;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/","/api/products/all","/api/category/all","/login","/register","/checkin","/checkout","/product-detail/**","/api/products/find/**","/api/checkout").permitAll()
			//.antMatchers("/api/category/create","/api/brand/create").hasAnyRole("Admin","Staff")
//			.antMatchers("/api/category/create","/api/category/edit/**").hasRole("Admin")
//			.antMatchers("/","/api/products/all","/login","/register","/checkin","/checkout","/product-detail/**","/api/products/find/**").permitAll()
//			.antMatchers("/api/category/create","/api/category/edit/**","/api/brand/create","/products/add/").hasAnyRole("Admin","Staff")
//			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
			.logout()
			.permitAll();
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/js/**","/fonts/**","/frontend/**","/img/**","/vendor/**","/upload-photos/**");
		
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		return new UserDetailServiceImp();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoauth);
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		authProvider.setUserDetailsService(userDetailsService());
		
		return authProvider;
	}
	
	
}
