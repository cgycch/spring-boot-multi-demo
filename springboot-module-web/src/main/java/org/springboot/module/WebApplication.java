package org.springboot.module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ComponentScan(basePackages = { "org.springboot.module" })
@MapperScan("org.springboot.module.mapper")
@SpringBootApplication
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })//禁用security not work
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class })//禁用security not work
public class WebApplication {
	public static void main(String[] args) {

		log.info("### web application begin start...");
		
		SpringApplication.run(WebApplication.class, args);

		log.info("### web application is running...");
	}
}
