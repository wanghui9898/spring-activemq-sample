package com.skyon.mq.producer;

import org.springframework.jms.core.JmsTemplate;

public class MessageSender {
	// ~~~ jmsTemplate  
    public JmsTemplate jmsTemplate;  
      
    /** 
     * send message 
     */  
    public void sendMessage(){  
        jmsTemplate.convertAndSend("hello jms!");  
    }  
    public void setJmsTemplate(JmsTemplate jmsTemplate) {  
        this.jmsTemplate = jmsTemplate;  
    }  
}
