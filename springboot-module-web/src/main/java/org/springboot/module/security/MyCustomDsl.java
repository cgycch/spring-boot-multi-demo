package org.springboot.module.security;

import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {

	private boolean flag;

	@Override
	public void init(HttpSecurity builder) throws Exception {
		super.init(builder);
		System.out.println("### MyCustomDsl.init()");
		builder.csrf().disable();
	}

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		super.configure(builder);
		//ApplicationContext context = builder.getSharedObject(ApplicationContext.class);
		//MyBaseFilter myFilter = context.getBean(MyBaseFilter.class);
		MyBaseFilter myFilter = new MyBaseFilter();
		myFilter.setFlag(flag);
		System.out.println("### MyCustomDsl.configure()");
		builder.authenticationProvider(new MyBaseAuthenticationProvider());
		//builder.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);
		//builder.addFilterAfter(myFilter, UsernamePasswordAuthenticationFilter.class);
		//builder.addFilterAt(myFilter, UsernamePasswordAuthenticationFilter.class);
	}

	public MyCustomDsl flag(boolean value) {
		this.flag = value;
		return this;
	}

	public static MyCustomDsl customDsl() {
		return new MyCustomDsl();
	}

}
