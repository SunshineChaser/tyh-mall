package edu.learning.tyh.item.dao;

import java.util.List;

import edu.learning.tyh.pojo.Porduct;
import edu.learning.tyh.pojo.ProductImg;

public interface ProductItemMapper {

	List<Porduct> selectItemList();
	
	Porduct selectItemById(String id);
	
	List<ProductImg> selectImgByPid(String id);
}
