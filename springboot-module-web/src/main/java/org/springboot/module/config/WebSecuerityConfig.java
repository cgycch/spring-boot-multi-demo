package org.springboot.module.config;

import java.util.Arrays;

import org.springboot.module.security.MyBaseFilter;
import org.springboot.module.security.MyCustomDsl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法级别的权限认证
public class WebSecuerityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("authenticationSuccessHandler")
	private AuthenticationSuccessHandler successHandler;

	@Autowired
	@Qualifier("authenticationFailHandler")
	private AuthenticationFailureHandler failHandler;

	@Autowired
	@Qualifier("authenticationEntryPointImpl")
	private AuthenticationEntryPoint entryPoint;
	
	// @Autowired
	// private IUserService userService;

	/**
	 * 重写该方法，设定用户访问权限
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		
//		// some basic configure
		http.cors().and().csrf().disable()
		.authorizeRequests()
		.antMatchers("/static/**").permitAll()
		.antMatchers("/api/security/open/**").hasRole("USER")
		.antMatchers("/api/security/limit/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		// some configure about login
		.and()
		.formLogin()
		.successHandler(successHandler)
		.failureHandler(failHandler)
		.permitAll()
//		// some configure about logout
//		.and()
//		.logout()
//		.permitAll()
		 .and()
		 .sessionManagement()
//		// some exception configure
//		.and()
//		//.addFilterBefore(new MyBaseFilter(), UsernamePasswordAuthenticationFilter.class)                           
//		//.addFilterAt(new MyBaseFilter(), UsernamePasswordAuthenticationFilter.class)                           
//		//.addFilterAfter(new MyBaseFilter(), UsernamePasswordAuthenticationFilter.class)
//		.exceptionHandling()
//		.accessDeniedPage("/login")
//		//.authenticationEntryPoint(entryPoint)
//		.and().apply(MyCustomDsl.customDsl())
//		//.flag(true)
//		//.disable()
//
		;
	}

	/**
	 * 重写该方法，添加自定义用户
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// type 1 simply use memory authentication
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		auth.inMemoryAuthentication().passwordEncoder(bCryptEncoder)
				.withUser("admin").password(bCryptEncoder.encode("admin")).roles("ADMIN")
				.and().withUser("cch").password(bCryptEncoder.encode("123456")).roles("USER");

		// type 2 use database authentication
		// auth.userDetailsService(userService).passwordEncoder(new
		// BCryptPasswordEncoder());
	}

	/**
	 * 配置CORS it not work well
	 * 
	 * @return
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("OPTIONS","GET","POST","PUT","PATH","DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("http://localhost:3000");
//		config.addAllowedHeader(CorsConfiguration.ALL);
//		config.addAllowedMethod(CorsConfiguration.ALL);
//		source.registerCorsConfiguration("/**", config);
//		CorsFilter corsFilter = new CorsFilter(source);
//		return corsFilter;
//	}
	
}
