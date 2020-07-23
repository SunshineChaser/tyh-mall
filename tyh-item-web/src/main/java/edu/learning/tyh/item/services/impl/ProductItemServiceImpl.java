package edu.learning.tyh.item.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.learning.tyh.item.dao.ProductItemMapper;
import edu.learning.tyh.item.dto.ProductItem;
import edu.learning.tyh.item.services.interfaces.ProductItemService;
import edu.learning.tyh.pojo.Porduct;
import edu.learning.tyh.pojo.ProductImg;

@Service
public class ProductItemServiceImpl implements ProductItemService{

	@Autowired
	private ProductItemMapper mapper;
	
	/**
	 * 1、查询出商品列表
	 * 2、遍历这个商品列表
	 * 3、查询出每一个商品的图片列表
	 * 4、将每一个商品封装成ProductItem对象
	 */
	@Override
	public List<ProductItem> selectProductItemList() {
		List<ProductItem>list=new ArrayList<>();
		
		//1
		List<Porduct>plist=mapper.selectItemList();
		//2
		for (Porduct porduct : plist) {
			ProductItem item=new ProductItem();
			//3
			List<ProductImg>imglist=mapper.selectImgByPid(porduct.getPid());
			//4
			item.setProduct(porduct);
			item.setImglist(imglist);
			list.add(item);
		}
		
		return list;
	}

	@Override
	public ProductItem selectProductItemById(String pid) {
		Porduct p=mapper.selectItemById(pid);
		List<ProductImg> imglist=mapper.selectImgByPid(pid);
		ProductItem item=new ProductItem();
		item.setProduct(p);
		item.setImglist(imglist);
		return item;
	}

}
