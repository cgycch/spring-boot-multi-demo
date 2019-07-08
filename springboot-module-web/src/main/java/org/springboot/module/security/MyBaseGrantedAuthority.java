package org.springboot.module.security;

import org.springframework.security.core.GrantedAuthority;

public class MyBaseGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = -4327870006900321927L;
	
	private String auhority;

	public MyBaseGrantedAuthority(String auhority) {
		this.auhority = auhority;
	}

	@Override
	public String getAuthority() {
		return this.auhority;
	}

}
