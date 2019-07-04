package org.springboot.module.controller.mock;

import org.springboot.module.vo.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/mock/api/jms")
public class Mock4Jms {
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@ApiOperation(value = "mock get", notes = "one rest api about jms message send")
	@GetMapping("/sent")
	public ResultSet<String> sent() {
		System.out.println("Mock4Jms.sent()...");
		jmsTemplate.convertAndSend("hello");
		ResultSet<String> result = new ResultSet<String>(1, "success", "success");
		return result;
	}

}
