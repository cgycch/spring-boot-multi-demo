package org.springboot.module.controller.mock;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "mock rest apis for redirect to my login page")
@CrossOrigin
@RestController
@RequestMapping(value = "/mock/")
public class Mock4Login {
	
	@ApiOperation(value = "mock 302 request")
	@GetMapping("/0612/302/request")
	public ResponseEntity<String> mock302request() throws URISyntaxException {
		System.out.println("Mock4Login.mock302request()...");
		return ResponseEntity.status(HttpStatus.FOUND).location(new URI("/mock/0612/loinPage")).body("{hello}");
	}
	@GetMapping("/0612/loinPage")
	public ResponseEntity<String> mockLoginPage(HttpServletResponse response) throws URISyntaxException {
		System.out.println("Mock4Login.mockLoginPage()...");
		ResponseEntity.accepted().contentType(MediaType.TEXT_HTML);
		response.setContentType("text/html");
		return ResponseEntity.status(HttpStatus.OK).body("<html><header><title>my login</title></header><body>hello world</body></html>");
	}

}
