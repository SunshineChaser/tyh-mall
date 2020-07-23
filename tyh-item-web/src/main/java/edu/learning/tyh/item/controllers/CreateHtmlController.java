package edu.learning.tyh.item.controllers;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import edu.learning.tyh.common.Config;
import edu.learning.tyh.item.dto.ProductItem;
import edu.learning.tyh.item.services.interfaces.ProductItemService;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Controller
@RequestMapping("/create")
public class CreateHtmlController {

	@Autowired
	private FreeMarkerConfig freemaker;
	
	@Autowired
	private ProductItemService service;
	
	@RequestMapping("/all")
	@ResponseBody
	public String createhtml() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Configuration conf=freemaker.getConfiguration();
		conf.setEncoding(Locale.getDefault(), "UTF-8");
		Template tmp=conf.getTemplate("item.ftl", "UTF-8");
		List<ProductItem>list=service.selectProductItemList();
		for (ProductItem productItem : list) {
			Map map=new HashMap();
			map.put("pro", productItem.getProduct());
			map.put("imgs", productItem.getImglist());
			map.put("fserver", Config.getFileserver());
			Writer out=new FileWriterWithEncoding(new File("E:\\eclipse_file\\edu_tyh\\itemhtml\\html\\"+productItem.getProduct().getPid()+".html"), "UTF-8");
			tmp.process(map, out);
			out.close();
		}
		
		return "ok";
	}
}
