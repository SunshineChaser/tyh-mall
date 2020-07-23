package edu.learning.tyh.manager.service.interfaces;

import java.util.List;

import edu.learning.tyh.pojo.ItemCat;

public interface ItemCatService {

	//增加商品类型
	int insertItemCat(ItemCat item);
	
	//修改商品类型
	int updateItemCat(ItemCat item);
	
	//根据id查询一条商品的信息
	ItemCat findItemCatById(String id);
	
	//查询商品列表
	//List<ItemCat> findItemCatList(ItemCat item);
	
	List<ItemCat> selectAll();
}
