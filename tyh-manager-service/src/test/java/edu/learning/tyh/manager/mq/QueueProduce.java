package edu.learning.tyh.manager.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueProduce {

	public static void main(String[] args) throws JMSException {
		//需要指定使用的协议，消息队列服务器的ip地址和端口号
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://120.76.137.138:61616");
		
		Connection connection=connectionFactory.createConnection();
		
		connection.start();
		
		Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Queue queue=session.createQueue("test-queue");
		
		MessageProducer producer=session.createProducer(queue);
		
		TextMessage textMessage=session.createTextMessage("hello ActiveMQ!!!");
		
		producer.send(textMessage);
		
		producer.close();
		session.close();
		connection.close();
	}
}
