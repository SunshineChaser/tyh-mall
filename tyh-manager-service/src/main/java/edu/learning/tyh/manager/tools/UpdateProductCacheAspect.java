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

import edu.learning.tyh.pojo.Porduct;


/**
 * 使用AOP,在进行数据库更新操作的时候，发送商品更新缓存消息到消息队列中
 * 
 * @author lenovo
 *
 */
@Aspect
@Component
public class UpdateProductCacheAspect {

	//定义切入点表达式
	@Pointcut("execution(* edu.learning.tyh.manager.service.impl.ProductServiceImpl.insertPro(..))")
	private void insertProPoint() {}
	
	@Pointcut("execution(* edu.learning.tyh.manager.service.impl.ProductServiceImpl.updateProduct(..))")
	private void updateProPoint() {}
	
	@Pointcut("insertProPoint() || updateProPoint()")
	private void updateProCachePoint() {}
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@Resource(name = "topicDestination")
	private Topic topic;


	@Around("updateProCachePoint()")
	public Object updateProCacheAOP(ProceedingJoinPoint joinPoint) throws Throwable {
		//获取被切入方法的参数
		Object[] args = joinPoint.getArgs();
		Porduct product=(Porduct) args[0];
		//获取商品首推状态
		int pfirst=product.getPfirst();
		final String catid = product.getCatid();
		// 首先执行被切入的原方法
		int res = (int) joinPoint.proceed();
		
		//检查被更新的这个商品是否是首推商品
		if(pfirst==1) {
			
			jmsTemplate.send(topic, new MessageCreator() {

				@Override
				public Message createMessage(Session session) throws JMSException {
					TextMessage textMessage = session.createTextMessage("edu.learning.tyh.home.service.ProductServiceImpl.selectProByFirst");
					return textMessage;
				}
			});
			
			jmsTemplate.send(topic, new MessageCreator() {

				@Override
				public Message createMessage(Session session) throws JMSException {
					TextMessage textMessage = session.createTextMessage("edu.learning.tyh.home.service.ProductServiceImpl.selectProByItemCat."+catid+" "+catid);
					return textMessage;
				}
			});
			
			System.out.println("消息已发送!");
		}

		return res;
	}
}
