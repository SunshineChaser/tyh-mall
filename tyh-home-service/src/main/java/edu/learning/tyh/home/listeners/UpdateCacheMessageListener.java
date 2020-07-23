package edu.learning.tyh.home.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import edu.learning.tyh.home.dao.ItemCatMapper;
import edu.learning.tyh.home.dao.PorductMapper;
import edu.learning.tyh.home.interfaces.ProductService;

/**
 * 更新缓存的监听器
 * @author lenovo
 *
 */
public class UpdateCacheMessageListener implements MessageListener{

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private PorductMapper promapper;
	
	@Autowired
	private ItemCatMapper catmapper;
	
	@Override
	public void onMessage(Message message) {
		//这里我想用MapMessage，通过判断布尔值来确定是否是已经删除的值，来减少查询数据库，但是没有找到MapMessage遍历键的用法
		TextMessage textMessage=(TextMessage) message;
		try {
			//发送的消息包含两部分，即redis_key+id，以空格分隔
			//例如:edu.learning.tyh.home.service.ProductServiceImpl.selectProByItemCat.1
			String text=textMessage.getText();
			System.out.println("监听到的消息为："+text);
			//获取发送的消息中空格的下标
			int spaceIndex=text.indexOf(" ");
			
			List<?>list;
			
			//发送的消息中不存在空格
			if(spaceIndex==-1) {
				//更新商品类型列表
				if(text.indexOf("selectItemCatList")!=-1) {
					list=catmapper.selectItemCatList();
					//从数据库中查询不到相关的数据，说明该数据不存在，则删除缓存中的数据
					if(list==null)redisTemplate.delete(text);
					redisTemplate.boundListOps(text).rightPushAll(list.toArray());
				}
				//更新首推商品列表
				if(text.indexOf("selectProByFirst")!=-1) {
					list=promapper.selectProByFirst();
					//从数据库中查询不到相关的数据，说明该数据不存在，则删除缓存中的数据
					if(list==null)redisTemplate.delete(text);
					redisTemplate.boundListOps(text).rightPushAll(list.toArray());
				}
			}
			else {//更新key类型的商品列表
				//获取Redis中数据的key
				String key=text.substring(0,spaceIndex);
				//获取需要更新的数据的id
				String id=text.substring(spaceIndex+1);
				list=promapper.selectByItemCat(id);
				//判断需要更新的数据状态，删除，修改，新增
				if(list!=null && list.size()!=0) {
					redisTemplate.boundListOps(key).rightPushAll(list.toArray());
					System.out.println("缓存数据已更新！");
				}
				else {
					redisTemplate.delete(key);
					System.out.println("缓存数据已删除！");
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
