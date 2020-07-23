package edu.learning.tyh.manager.dao;

import java.util.List;

import edu.learning.tyh.pojo.ProductImg;

public interface ProductImgMapper {
	int deleteByPrimaryKey(String pimgid);

	int insert(ProductImg record);

	int insertSelective(ProductImg record);

	ProductImg selectByPrimaryKey(String pimgid);

	int updateByPrimaryKeySelective(ProductImg record);

	int updateByPrimaryKey(ProductImg record);

	/**
	 * 根据商品id查询商品的图片列表
	 * 
	 * @param pid
	 * @return
	 */
	List<ProductImg> selectPImgList(String pid);
}