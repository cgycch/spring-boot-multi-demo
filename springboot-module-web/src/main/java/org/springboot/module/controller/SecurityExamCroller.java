package org.springboot.module.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springboot.module.vo.ResultSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Exmaple API for security Demo ")
@RestController
@RequestMapping(value = "/api/security")
public class SecurityExamCroller {
	
	@ApiOperation(value = "/open", notes = " access with role user")
	@GetMapping(value = "/open")
	public ResultSet<String> testOpen() {
		System.out.println("SecurityExamCroller.testOpen()");
		return ResultSet.newSuccessResult("hello user");
	}
	
	@ApiOperation(value = "/limit", notes = " access with role admin")
	@GetMapping(value = "/limit")
	public ResultSet<String> testLimit() {
		System.out.println("SecurityExamCroller.testLimit()");
		return ResultSet.newSuccessResult("hello admin");
	}
	
	@ApiOperation(value = "/isLogin", notes = " access with role admin")
	@GetMapping(value = "/isLogin")
	public ResultSet<String> hasLogin(HttpServletRequest request) {
		System.out.println("SecurityExamCroller.hasLogin()");
		HttpSession session = request.getSession(false);
		if(session == null) {
			System.out.println("session is null");
		}else {
			String id = session.getId();
			System.out.println("session id is: "+ id);
			Object username = session.getAttribute("username");
			if(username == null) {
				System.out.println("username has not setting...");
				session.setAttribute("username", "Guest");
			}else {
				System.out.println("username is: " + username);
			}
		}
		return ResultSet.newSuccessResult("success");
	}

}
