package edu.learning.tyh.item.services.interfaces;

import java.util.List;

import edu.learning.tyh.item.dto.ProductItem;

public interface ProductItemService {

	List<ProductItem> selectProductItemList();
	
	ProductItem selectProductItemById(String pid);
}
