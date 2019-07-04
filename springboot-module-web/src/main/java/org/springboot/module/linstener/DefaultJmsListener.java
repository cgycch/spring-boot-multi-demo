package org.springboot.module.linstener;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultJmsListener implements ExceptionListener{
	
	private String name;
	
	public DefaultJmsListener(String name){
		this.name = name;
	}
	

	@Override
	public void onException(JMSException exception) {
		log.error("an jms exception was encountered !!!", exception);
		log.error("connection factory name is {}", name);
		log.error("try to do something here....");		
	}

}
