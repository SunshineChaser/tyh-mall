package edu.learning.tyh.manager.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicProduce {

	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://120.76.137.138:61616");
		
		Connection connection=connectionFactory.createConnection();
		
		connection.start();
		
		Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Topic topic=session.createTopic("test-topic");
		MessageProducer producer=session.createProducer(topic);
		TextMessage textMessage=session.createTextMessage("this is a topic message!!!");
		producer.send(textMessage);
		
		producer.close();
		session.close();
		connection.close();
	}
}
