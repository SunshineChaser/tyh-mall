package edu.learning.tyh.item.listeners;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import edu.learning.tyh.common.Config;
import edu.learning.tyh.item.dto.ProductItem;
import edu.learning.tyh.item.services.interfaces.ProductItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 接受商品信息的监听器
 * @author lenovo
 *
 */
public class ProductMessageListener implements MessageListener{

	@Autowired
	private ProductItemService service;
	
	@Autowired
	private FreeMarkerConfig freemaker;
	/**
	 * msg:所接受到的消息
	 */
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage=(TextMessage) message;
		try {
			ProductItem item=service.selectProductItemById(textMessage.getText());
			Configuration conf=freemaker.getConfiguration();
			conf.setEncoding(Locale.getDefault(), "UTF-8");
			Template tmp=conf.getTemplate("item.ftl", "UTF-8");
			Map map=new HashMap();
			map.put("pro", item.getProduct());
			map.put("imgs", item.getImglist());
			map.put("fserver", Config.getFileserver());
			Writer out=new FileWriterWithEncoding(new File("E:\\eclipse_file\\edu_tyh\\itemhtml"+item.getProduct().getPid()+".html"), "UTF-8");
			tmp.process(map, out);
			out.close();
			System.out.println("create html success!");
		} catch (JMSException | IOException | TemplateException e) {
			e.printStackTrace();
		}
	}

}
