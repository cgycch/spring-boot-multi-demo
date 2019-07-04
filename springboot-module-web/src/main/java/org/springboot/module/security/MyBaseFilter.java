package org.springboot.module.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Component
public class MyBaseFilter extends AbstractAuthenticationProcessingFilter {
	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
	
	
	public MyBaseFilter() {
		 super(new AntPathRequestMatcher("/**"));
		 //super(new AntPathRequestMatcher("/api/hello/*"));
		 System.out.println("### MyBaseFilter.MyBaseFilter()");
		 authenticationManager = new MyBaseAuthenticationManager();
		 //super.setAuthenticationManager(authenticationManager);
	}
	
	private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
	private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
	
	private AuthenticationManager authenticationManager;
	
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}
	
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("### MyBaseFilter.doFilter()");
		chain.doFilter(req,res); 
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		System.out.println("### MyBaseFilter.attemptAuthentication()");


		String username = request.getParameter(usernameParameter);
		String password = request.getParameter(passwordParameter);
		
		System.out.println("userName: " + username);
		System.out.println("password: " + password);
		
		
		//MyBaseAuthentication authRequest = new MyBaseAuthentication(username, password);
		//Authentication authenticate = this.getAuthenticationManager().authenticate(authRequest);
		//System.out.println("authenticate is not null: " + authenticate != null);
		//if(authenticate == null) {
		//	authenticate = new MyBaseAuthentication("cch", "123456"); 
		//}
		Authentication authenticate = new UsernamePasswordAuthenticationToken("cch", "123456");
		//Authentication authenticate = new MyBaseAuthentication("cch", "123456");
		//test
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		return authenticate;
	}

	private boolean flag;
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean getFlag() {
		return flag;
	}
}
