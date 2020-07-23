package edu.learning.tyh.home.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.learning.tyh.common.Config;
import edu.learning.tyh.home.interfaces.ProductService;
import edu.learning.tyh.pojo.ItemCat;
import edu.learning.tyh.pojo.Porduct;

/**
 * 首页控制层
 * @author lenovo
 *
 */
@Controller
public class HomeController {

	@Autowired
	ProductService productService;
	
	/**
	 * 初始化首推商品列表
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@RequestMapping("/home")
	public String tohome(Model model) throws FileNotFoundException, IOException {
		model.addAttribute("fserver", Config.getFileserver());
		return "home/home";
	}
	
	/**
	 * 获取首推商品列表
	 * @return
	 */
	@RequestMapping("/getFirstProduct")
	@ResponseBody
	public List<Porduct> getFirstProduct() {
		return productService.selectProByFirst();
	}
	
	/**
	 * 获取商品类型列表
	 * @return
	 */
	@RequestMapping("/getItemCatList")
	@ResponseBody
	public List<ItemCat> getItemCatList() {
		return productService.selectItemCatList();
	}
	
	/**
	 * 根据商品类型获取对应的商品列表
	 * @return
	 */
	@RequestMapping("/productList/{catid}")
	@ResponseBody
	public List<Porduct> getProductByItemCat(@PathVariable String catid){
		return productService.selectProByItemCat(catid);
	}
}
