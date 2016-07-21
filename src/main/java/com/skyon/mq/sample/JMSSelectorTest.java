package com.skyon.mq.sample;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class JMSSelectorTest {
	
	public static void main(String[] args) throws JMSException {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = factory.createConnection();
		connection.start();
		Queue queue = new ActiveMQQueue("testQueue");
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageConsumer comsumerA = session.createConsumer(queue, "receiver = 'A'");
		comsumerA.setMessageListener(new MessageListener() {
			public void onMessage(Message m) {
				try {
					System.out.println("ConsumerA get " + ((TextMessage) m).getText());
				} catch (JMSException e1) {
				}
			}
		});
		MessageConsumer comsumerB = session.createConsumer(queue, "receiver = 'B'");
		comsumerB.setMessageListener(new MessageListener() {
			public void onMessage(Message m) {
				try {
					System.out.println("ConsumerB get " + ((TextMessage) m).getText());
				} catch (JMSException e) {
				}
			}
		});
		MessageProducer producer = session.createProducer(queue);
		for (int i = 0; i < 10; i++) {
			String receiver = (i % 3 == 0 ? "A" : "B");
			TextMessage message = session.createTextMessage("Message" + i + ", receiver:" + receiver);
			message.setStringProperty("receiver", receiver);
			producer.send(message);
		}
	}
 }  
