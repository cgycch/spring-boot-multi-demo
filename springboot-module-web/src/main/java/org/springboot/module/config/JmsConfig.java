package org.springboot.module.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springboot.module.linstener.DefaultJmsListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class JmsConfig {

	@Bean
	public ConnectionFactory connectionFactory() {
		log.info("#### connectionFactory start");
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL("failover:(tcp://localhost:61616)?timeout=3000&startupMaxReconnectAttempts=11&maxReconnectAttempts=11");
		//connectionFactory.setBrokerURL("tcp://localhost:61616");
		connectionFactory.setUserName("admin");
		connectionFactory.setPassword("admin");
		connectionFactory.setSendTimeout(10 * 1000);
		log.info("#### connectionFactory success");
		return connectionFactory;
	}

	@Bean
	public ConnectionFactory cachingConnectionFactory() {
		log.info("#### cachingConnectionFactory start");
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setTargetConnectionFactory(connectionFactory());
		connectionFactory.setSessionCacheSize(1);
		connectionFactory.setReconnectOnException(true);
		connectionFactory.setExceptionListener(new DefaultJmsListener("myQueue"));
		log.info("#### cachingConnectionFactory success");
		return connectionFactory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		log.info("#### jmsTemplate start");
		JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory());
		jmsTemplate.setDefaultDestinationName("myQueue");
		jmsTemplate.setTimeToLive(10 * 1000);
		log.info("#### jmsTemplate success");
		return jmsTemplate;
	}

}
