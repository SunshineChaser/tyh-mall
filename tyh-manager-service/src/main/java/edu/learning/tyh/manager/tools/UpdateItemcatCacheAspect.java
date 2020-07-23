package edu.learning.tyh.manager.tools;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * 使用AOP,在进行数据库更新操作的时候，发送商品类型更新缓存消息到消息队列中
 * 
 * @author lenovo
 *
 */
@Aspect
@Component
public class UpdateItemcatCacheAspect {

	//定义切入点表达式
	@Pointcut("execution(* edu.learning.tyh.manager.service.impl.ItemCatServiceImpl.insertItemCat(..))")
	private void insertItemCatPoint() {}
	
	@Pointcut("execution(* edu.learning.tyh.manager.service.impl.ItemCatServiceImpl.updateItemCat(..))")
	private void updateItemCatPoint() {}
	
	@Pointcut("insertItemCatPoint() || updateItemCatPoint()")
	private void updateItemCatCachePoint() {}
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@Resource(name = "topicDestination")
	private Topic topic;

	@Around("updateItemCatCachePoint()")
	public Object updateItemCatCacheAOP(ProceedingJoinPoint joinPoint) throws Throwable {
		// 首先执行被切入的原方法
		int res = (int) joinPoint.proceed();

		jmsTemplate.send(topic, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage("edu.learning.tyh.home.service.ProductServiceImpl.selectItemCatList");
				return textMessage;
			}
		});
		System.out.println("消息已发送!");

		return res;
	}
}
