package edu.learning.tyh.manager.mq;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueConsumer {

	public static void main(String[] args) throws JMSException, IOException {
ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://120.76.137.138:61616");
		
		Connection connection=connectionFactory.createConnection();
		
		connection.start();
		
		Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Queue queue=session.createQueue("test-queue");
		
		MessageConsumer consumer=session.createConsumer(queue);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message msg) {
				TextMessage textMessage=(TextMessage) msg;
				try {
					String text=textMessage.getText();
					System.out.println(text);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		
		System.in.read();
		
		consumer.close();
		session.close();
		connection.close();
	}
}
