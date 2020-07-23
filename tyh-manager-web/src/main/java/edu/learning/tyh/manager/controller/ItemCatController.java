package edu.learning.tyh.manager.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.learning.tyh.manager.service.interfaces.ItemCatService;
import edu.learning.tyh.pojo.ItemCat;

@Controller
@RequestMapping("/manager/itemcat")
public class ItemCatController {

	@Autowired
	private ItemCatService service;
	
	@RequestMapping("/details/{id}")
	@ResponseBody
	public ItemCat findItemCatById(@PathVariable String id){
		return service.findItemCatById(id);
	}
	
	/**
	 * 进入到增加页面的controller
	 * @return
	 */
	@RequestMapping("/toadd")
	public String toadd() {
		return "itemcat/add";
	}
	
	/**
	 * 增加商品分类
	 * @param cat
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(ItemCat cat,Model model) {
		cat.setCatid(UUID.randomUUID().toString());
		int rtn=service.insertItemCat(cat);
		
		if(rtn>0) {
			return "redirect:/manager/itemcat/list";
		}else {
			model.addAttribute("msg","增加商品类型失败！");
			return "itemcat/toadd";
		}
	}
	
	
	/**
	 * 进入到商品类型列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<ItemCat> list=service.selectAll();
		model.addAttribute("list", list);
		return "itemcat/list";
	}
	
	/**
	 * 初始化商品类型页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toedit/{id}")
	public String toedit(@PathVariable String id,Model model) {
		ItemCat itemCat=service.findItemCatById(id);
		model.addAttribute("item", itemCat);
		
		return "itemcat/edit";
	}
	
	@RequestMapping("/edit")
	public String edit(ItemCat cat,Model model) {
		int rtn=service.updateItemCat(cat);
		if(rtn>0) {
			return "redirect:/manager/itemcat/list";
		}else {
			model.addAttribute("msg", "修改商品类型失败！");
			return "itemcat/edit";
		}
	}
	
}
