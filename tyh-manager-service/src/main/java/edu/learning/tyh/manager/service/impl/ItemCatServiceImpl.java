package edu.learning.tyh.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.learning.tyh.manager.dao.ItemCatMapper;
import edu.learning.tyh.manager.service.interfaces.ItemCatService;
import edu.learning.tyh.pojo.ItemCat;

@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private ItemCatMapper mapper;
	
	@Override
	public int insertItemCat(ItemCat item) {
		// TODO 自动生成的方法存根
		return mapper.insert(item);
	}

	@Override
	public int updateItemCat(ItemCat item) {
		// TODO 自动生成的方法存根
		return mapper.updateByPrimaryKey(item);
	}

	@Override
	public ItemCat findItemCatById(String id) {
		// TODO 自动生成的方法存根
		return mapper.selectByPrimaryKey(id);
	}

	/*@Override
	public List<ItemCat> findItemCatList(ItemCat item) {
		// TODO 自动生成的方法存根
		return null;
	}*/

	@Override
	public List<ItemCat> selectAll() {
		// TODO 自动生成的方法存根
		return mapper.selectAll();
	}

	
}
