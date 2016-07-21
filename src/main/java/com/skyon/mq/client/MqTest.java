package com.skyon.mq.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.skyon.mq.producer.MessageSender;

public class MqTest {
	
	public static void main(String[] args) {
		System.out.println("初始化spring！准备开始接收！");  
	    ApplicationContext context = new ClassPathXmlApplicationContext("p2s.xml");  
	    MessageSender t=(MessageSender) context.getBean("topicMessageSender");  
	    t.sendMessage(); 
	}
}
